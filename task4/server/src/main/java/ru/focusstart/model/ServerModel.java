package ru.focusstart.model;

import ru.focusstart.connection.ConnectionParameter;
import ru.focusstart.contactlist.ContactList;
import ru.focusstart.jsonobject.JSONObject;
import ru.focusstart.reader.PropertieReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.List;
import java.util.concurrent.*;

public class ServerModel {
    private static ServerModel instance;
    private int portNumber;
    //private boolean inWork;
    ServerSocket serverSocket;
    List<ConnectionParameter> onLineConnections;
    BlockingQueue<ConnectionParameter> processConnections;
    /*Thread WaitConnectionThread;
    Thread messageListenerThread;
    Thread handleThread;*/
    ExecutorService executorService;

    //private static final Logger log = LoggerFactory.getLogger(ServerModel.class);

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
        //this.inWork = false;
    }

    public void addConnection(ConnectionParameter connection) {
        this.onLineConnections.add(connection);
    }

    public void removeConnection(ConnectionParameter connection) {
        this.onLineConnections.remove(connection);
    }
/*
    public JSONObject getJSONObject(String message) {
        if (message != null && !message.isEmpty()) {
            JSONDeserialization deserialization = new JSONDeserialization();
            return deserialization.deserialize(message);
        }
        return null;
    }*/

    private String readMessage(BufferedReader reader) throws IOException {
       /* if (reader.ready()) {
            System.out.println("Пытаемся читать");
            return reader.readLine();
        }
        return "";*/
        return reader.readLine();
    }

    public void sendMessage(PrintWriter writer, JSONObject jsonObject) {
        writer.println(jsonObject.serialize());
        writer.flush();
    }

    public void sendMessageToEveryone(JSONObject jsonObject) {
        for (ConnectionParameter connection :
                onLineConnections) {
            sendMessage(connection.getWriter(), jsonObject);
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
/*
    public void handleConnectionParameter() {
        Thread handleThread = new Thread(() -> {

            try {
                ConnectionParameter connectionParameter = this.processConnections.take();
                BufferedReader reader = connectionParameter.getReader();
                if (reader != null) {
                    String str = this.readMessage(reader);
                    if (!str.isEmpty()) {
                        System.out.println(str);
                        JSONObject jsonObject = this.getJSONObject(str);
                        if (jsonObject != null) {
                            System.out.println("Сообщение принято");
                            connectionParameter.setJsonObject(jsonObject);
                            ConnectionHandler connectionHandler = ConnectionHandlers.valueOf(jsonObject.getOwnName()).getConnectionHandler();
                            System.out.println(connectionHandler.getClass().getSimpleName());
                            connectionHandler.handle(connectionParameter);
                        }
                    }
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();

        });
        handleThread.start();
    }}*/

    public ContactList getContactList() {
        ContactList contactList = new ContactList();
        for (ConnectionParameter connection :
                onLineConnections) {
            contactList.add(connection.getNickname());
        }
        return contactList;
    }
/*
    public void waitConnection() {
        Thread WaitConnectionThread = new Thread(() -> {
            while (this.inWork) {
                Socket clientSocket;
                try {
                    clientSocket = serverSocket.accept();
                    System.out.println("Попытка подключения");
                    ConnectionParameter connectionParameter = new ConnectionParameterBuilder()
                            .buildSocket(clientSocket)
                            .buildReader(new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
                            .buildWriter(new PrintWriter(clientSocket.getOutputStream())).build();
                    this.processConnections.put(connectionParameter);
                    //this.handleConnectionParameter(connectionParameter);
                } catch (IOException | InterruptedException e) {
                    //log.info(e.getMessage());
                    System.out.println(e.getMessage());
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        WaitConnectionThread.start();
    }*/
/*
    private void listenToClients() {
        Thread messageListenerThread = new Thread(() -> {
            //boolean interrupted = false;
            while (this.inWork) {
                // System.out.println("слушаю клиентов");
                if (!onLineConnections.isEmpty()) {
                    for (ConnectionParameter connection :
                            onLineConnections) {
                        try {
                            BufferedReader reader = connection.getReader();
                            if (reader != null) {
                                String str = this.readMessage(reader);
                                if (!str.isEmpty()) {
                                    connection.setJsonstring(str);
                                    this.processConnections.put(connection);
                                }
                            }
                        } catch (InterruptedException | IOException e) {
                            //log.info(e.getMessage());
                            System.out.println(e.getMessage());
                        }
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        messageListenerThread.start();
    }*/

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
        this.executorService.execute(new ClientWaiters(serverSocket, processConnections));
        this.executorService.execute(new ClientsHandler(processConnections, onLineConnections));
        this.executorService.execute(new Handler(processConnections));
    /*
        this.waitConnection();
        this.listenToClients();
        this.handleConnectionParameter();*/
    }

    public void stop() throws IOException {
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

        if (!this.serverSocket.isClosed()) {
            this.serverSocket.close();
        }
        //TODO отправить всем сервисное сообщение что сервер остановлен
        //log.info(serverSocket.toString());
        if (!onLineConnections.isEmpty()) {
            for (ConnectionParameter connection :
                    onLineConnections) {
                if (connection.getSocket().isConnected()) {
                    connection.close();
                    //log.info(connection.toString());
                }
            }
        }

    }
}
