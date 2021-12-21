package ru.focusstart.model;

import ru.focusstart.connection.ConnectionParameter;
import ru.focusstart.contactlist.ContactList;
import ru.focusstart.jsonobject.JSONObject;
import ru.focusstart.message.ServerStopServiceMessage;
import ru.focusstart.reader.PropertieReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.List;
import java.util.concurrent.*;

public class ServerModel {
    private static ServerModel instance;
    private int portNumber;
    ServerSocket serverSocket;
    List<ConnectionParameter> onLineConnections;
    BlockingQueue<ConnectionParameter> processConnections;
    /*Thread WaitConnectionThread;
    Thread messageListenerThread;
    Thread handleThread;*/
    ExecutorService executorService;

    public static ServerModel getInstance() throws IOException {
        if (instance == null) {
            instance = new ServerModel();
        }
        return instance;
    }

    private ServerModel() throws IOException {
        PropertieReader portNumberReader = new PropertieReader(ServerModel.class, "/server.properties");
        this.portNumber = Integer.parseInt(portNumberReader.read("server.port"));
        this.onLineConnections = new CopyOnWriteArrayList<>();
        this.processConnections = new SynchronousQueue<>();
    }

    public void addConnection(ConnectionParameter connection) {
        this.onLineConnections.add(connection);
    }

    public void removeConnection(ConnectionParameter connection) {
        this.onLineConnections.remove(connection);
    }


    public void sendMessage(PrintWriter writer, JSONObject jsonObject) {
        if (writer != null) {
            writer.println(jsonObject.serialize());
            writer.flush();
        }
    }

    public void sendMessageToEveryone(JSONObject jsonObject) {
        if (!onLineConnections.isEmpty()) {
            for (ConnectionParameter connection :
                    onLineConnections) {
                sendMessage(connection.getWriter(), jsonObject);
            }
        }
    }

    public boolean checkNickname(String nickname) {
        for (ConnectionParameter connection :
                onLineConnections) {
            if (nickname.equals(connection.getNickname())) {
                return true;
            }
        }
        return false;
    }

    public ContactList getContactList() {
        ContactList contactList = new ContactList();
        for (ConnectionParameter connection :
                onLineConnections) {
            contactList.add(connection.getNickname());
        }
        return contactList;
    }

    public void start() throws IOException {
        this.serverSocket = new ServerSocket(portNumber);
        //this.inWork = true;
      /*  this.WaitConnectionThread = new Thread(new ClientWaiters(serverSocket, processConnections));
        this.messageListenerThread = new Thread(new ClientsListner(processConnections, onLineConnections));
        this.handleThread = new Thread(new Handler(processConnections));
        this.WaitConnectionThread.start();
        this.messageListenerThread.start();
        this.handleThread.start();*/

        this.executorService = Executors.newFixedThreadPool(3);
        this.executorService.execute(new WaitingForConnection(serverSocket, processConnections));
        this.executorService.execute(new WaitingForMessage(processConnections, onLineConnections));
        this.executorService.execute(new MessageProcessing(processConnections));
    /*
        this.waitConnection();
        this.listenToClients();
        this.handleConnectionParameter();*/
    }

    public void stop() {
        this.sendMessageToEveryone(new ServerStopServiceMessage());
        executorService.shutdownNow();
        //this.inWork = false;
       /* this.WaitConnectionThread.interrupt();
        this.messageListenerThread.interrupt();
        this.handleThread.interrupt();*/
        /*
        try {
            this.WaitConnectionThread.join();
            this.messageListenerThread.join();
            this.handleThread.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }*/

        try {
            if (!this.serverSocket.isClosed()) {
                this.serverSocket.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (!onLineConnections.isEmpty()) {
                for (ConnectionParameter connection :
                        onLineConnections) {
                    if (connection.getSocket().isConnected()) {
                        connection.close();
                    }
                }
            }
        }
    }
}
