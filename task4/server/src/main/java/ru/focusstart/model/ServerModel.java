package ru.focusstart.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.focusstart.reader.PropertieReader;
import ru.focusstart.contactlist.ContactList;
import ru.focusstart.encryption.Encryption;
import ru.focusstart.login.Login;
import ru.focusstart.message.Message;
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
    List<Socket> clients;
    //List<String> nickNames;
    ContactList nickNames;
    List<BufferedReader> readers;
    List<PrintWriter> writers;
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
        clients = new ArrayList<>();
        readers = new ArrayList<>();
        writers = new ArrayList<>();
        nickNames = new ContactList();
        concoleReader = new BufferedReader(new InputStreamReader(System.in));
        inWork = false;
    }

    private void listenToConcole() {
        Thread commandListenerThread = new Thread(() -> {
            while (inWork) {
                try {
                    String command;
                    // if (this.concoleReader.ready()) {
                    command = this.concoleReader.readLine();
                    if (command.equals("server stop")) {
                        this.serverSocket.close();
                        log.info(serverSocket.toString());
                        if (!clients.isEmpty()) {
                            for (Socket socket : clients) {
                                if (socket.isConnected()) {
                                    socket.close();
                                    log.info(socket.toString());
                                }
                            }
                        }
                        this.inWork = false;
                        break;
                    }
                    // }
                } catch (IOException e) {
                    log.info(e.getMessage());
                }
            }
        });
        commandListenerThread.start();
    }

    private void listenToClients() {
        Thread messageListenerThread = new Thread(() -> {
            boolean interrupted = false;
            while (!interrupted) {
                try {
                    String stringMessage = readMessage();
                    if (!stringMessage.isEmpty()) {
                        // System.out.println(stringMessage);
                        JSONDeserialization deserializationMessage = new JSONDeserialization();
                        Message message = deserializationMessage.deserializeMessage(stringMessage);
                        // Message message = new ObjectMapper().readValue(stringMessage, Message.class);
                        if (message != null) {
                            this.sendMessageToEveryone(message);
                        }
                    }
                } catch (IOException e) {
                    log.info(e.getMessage());
                    //System.out.println("Ошибка в сереализации");
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    interrupted = true;
                }
            }
        });
        messageListenerThread.start();
    }

    public String readMessage() throws IOException {
        for (BufferedReader reader : readers) {
            if (reader.ready()) {
                return reader.readLine();
            }
        }
        return "";
    }

    public void sendMessage(PrintWriter writer, Encryption encryptionObject) {
        writer.println(encryptionObject.serialize());
        writer.flush();
    }

    public void sendMessageToEveryone(Encryption encryptionObject) {
        for (PrintWriter writer : writers) {
            sendMessage(writer, encryptionObject);
           /* writer.println(encryptionObject.serialize());
            writer.flush();*/
        }
    }

    public void start() {
        try {
            this.serverSocket = new ServerSocket(portNumber);
            inWork = true;
            this.listenToClients();
            this.listenToConcole();
        } catch (IOException e) {
            log.info(e.getMessage());
            //System.out.println(e.getMessage());
        }
        while (inWork) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                String message = reader.readLine();
                JSONDeserialization deserializationLogin = new JSONDeserialization();
                Login login = deserializationLogin.deserializeLogin(message);
                String userNickName = login.getUserNickname();
                if (nickNames.contains(userNickName)) {
                    this.sendMessage(writer, new Message("Ник " + userNickName + " занят"));
                    throw new IllegalArgumentException("Ник " + userNickName + " занят");
                }
                nickNames.add(userNickName);
                readers.add(reader);
                writers.add(writer);
                clients.add(clientSocket);
                this.sendMessageToEveryone(nickNames);
                this.sendMessageToEveryone(new Message(userNickName + " присоединился к чату"));
                log.info(clientSocket.toString());
            } catch (IOException | IllegalArgumentException e) {
                log.info(e.getMessage());
            }
        }
    }
}
