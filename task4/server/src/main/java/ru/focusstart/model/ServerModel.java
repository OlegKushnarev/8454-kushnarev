package ru.focusstart.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reader.PropertieReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerModel {
    private static ServerModel instance;
    private int portNumber;
    ServerSocket serverSocket;
    List<Socket> clients;
    List<BufferedReader> readers;

    private static final Logger log = LoggerFactory.getLogger(ServerModel.class);

    public static ServerModel getInstance() {
        if (instance == null) {
            try {
                instance = new ServerModel();
            } catch (IOException e) {
                //  System.setProperty("log.name", String.valueOf(ServerModel.class.getResource("/log/server.log")));
                log.info(e.getMessage());
            }
        }
        return instance;
    }

    private ServerModel() throws IOException {
        PropertieReader portNumberReader = new PropertieReader(ServerModel.class, "/server.properties");
        this.portNumber = portNumberReader.read("server.port");
        clients = new ArrayList<>();
        readers = new ArrayList<>();
        //writers =  new ArrayList<>();
    }

    public void start() {
        while (true) {
            Socket clientSocket = null;
            try {
                this.serverSocket = new ServerSocket(portNumber);
                clientSocket = serverSocket.accept();
                readers.add(new BufferedReader(new InputStreamReader(clientSocket.getInputStream())));
                log.info(clientSocket.toString());
            } catch (IOException e) {
                log.info(e.getMessage());
            }
            clients.add(clientSocket);
        }
    }

    public void watch() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            log.info(e.getMessage());
        } finally {
            for (Socket socket : clients) {
                try {
                    socket.close();
                } catch (IOException e) {
                    log.info(e.getMessage());
                }
            }
        }
    }
}
