package ru.focusstart.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.focusstart.connection.ConnectionParameter;
import ru.focusstart.connection.ConnectionParameterBuilder;
import ru.focusstart.contactlist.ContactList;
import ru.focusstart.jsonobject.JSONObject;
import ru.focusstart.reader.PropertieReader;
import ru.focusstart.serialization.JSONDeserialization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerModel {
    private static ServerModel instance;
    private int portNumber;
    private boolean inWork;
    ServerSocket serverSocket;
    List<ConnectionParameter> connections;
    //List<Socket> clients;
    //List<String> nickNames;
    //ContactList nickNames;
    //List<BufferedReader> readers;
    //List<PrintWriter> writers;
    BufferedReader concoleReader;

    private static final Logger log = LoggerFactory.getLogger(ServerModel.class);

    public static ServerModel getInstance() {
        if (instance == null) {
            try {
                instance = new ServerModel();
            } catch (IOException e) {
                System.setProperty("log.name", String.valueOf(ServerModel.class.getResource("/log/server.log")));
                log.info(e.getMessage());
            }
        }
        return instance;
    }

    private ServerModel() throws IOException {
        PropertieReader portNumberReader = new PropertieReader(ServerModel.class, "/server.properties");
        this.portNumber = Integer.parseInt(portNumberReader.read("server.port"));
        this.connections = new ArrayList<>();
        this.concoleReader = new BufferedReader(new InputStreamReader(System.in));
        this.inWork = false;
    }

    public void addConnection(ConnectionParameter connection) {
        this.connections.add(connection);
    }

    public void removeConnection(ConnectionParameter connection) {
        this.connections.remove(connection);
    }

    public void stop() {
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            e.getMessage();
        }
        log.info(serverSocket.toString());
        if (!connections.isEmpty()) {
            for (ConnectionParameter connection :
                    connections) {
                if (connection.getSocket().isConnected()) {
                    connection.close();
                    log.info(connection.toString());
                }
            }
        }
        this.inWork = false;
    }

    private void listenToClients() {
        Thread messageListenerThread = new Thread(() -> {
            //boolean interrupted = false;
            while (this.inWork) {
                // System.out.println("слушаю клиентов");
                if (!connections.isEmpty()) {
                    for (ConnectionParameter connection :
                            connections) {
                        try {
                            this.handleConnectionParameter(connection);
                        } catch (IOException e) {
                            log.info(e.getMessage());
                            System.out.println(e.getMessage());
                        }
                    }
                }
                try {
                    //System.out.println("Засыпаю");
                    Thread.sleep(100);
                    // System.out.println("Проснулся");
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    //interrupted = true;
                }
            }
        });
        messageListenerThread.start();
    }

    public JSONObject getJSONObject(String message) {
        if (!message.isEmpty()) {
            JSONDeserialization deserialization = new JSONDeserialization();
            return deserialization.deserialize(message);
        }
        return null;
    }

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
                connections) {
            sendMessage(connection.getWriter(), jsonObject);
        }
    }

    public boolean checkNickname(String nickname) {
        for (ConnectionParameter connection :
                connections) {
            if (nickname.equals(connection.getNickname())) {
                return true;
            }
        }
        return false;
    }

    public synchronized void handleConnectionParameter(ConnectionParameter connectionParameter) throws IOException {
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
            } else {
                System.out.println("Строка пуста!");
            }
        } else {
            System.out.println("Ридер пустой!");
        }
    }

    public ContactList getContactList() {
        ContactList contactList = new ContactList();
        for (ConnectionParameter connection :
                connections) {
            contactList.add(connection.getNickname());
        }
        return contactList;
    }

    public void waitConnection() {
        Thread WaitConnectionThread = new Thread(() -> {
            while (inWork) {
                Socket clientSocket;
                try {
                    clientSocket = serverSocket.accept();
                    System.out.println("Попытка подключения");
                    ConnectionParameter connectionParameter = new ConnectionParameterBuilder()
                            .buildSocket(clientSocket)
                            .buildReader(new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
                            .buildWriter(new PrintWriter(clientSocket.getOutputStream())).build();
                    this.handleConnectionParameter(connectionParameter);
                } catch (IOException | IllegalArgumentException e) {
                    log.info(e.getMessage());
                }
            }
        }, "Ожидающий подключение");
        WaitConnectionThread.start();
    }

    public void start() {
        try {
            this.serverSocket = new ServerSocket(portNumber);
            this.inWork = true;
            this.waitConnection();
            this.listenToClients();
            this.stop();
        } catch (IOException e) {
            log.info(e.getMessage());
            System.out.println(e.getMessage());
        }
    }
}
