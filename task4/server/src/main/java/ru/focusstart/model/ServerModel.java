package ru.focusstart.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reader.PropertieReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerModel {
    private static ServerModel instance;
    private int portNumber;
    ServerSocket serverSocket;
    List<Socket> clients;
    private static final Logger log = LoggerFactory.getLogger(ServerModel.class);

    public static ServerModel getInstance() {
        if (instance == null) {
            try {
                instance = new ServerModel();
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        }
        return instance;
    }

    private ServerModel() throws IOException {
        PropertieReader portNumberReader = new PropertieReader(ServerModel.class, "/server.properties");
        this.portNumber = portNumberReader.read("server.port");
        this.serverSocket = new ServerSocket(portNumber);
        clients = new ArrayList<>();
    }

    public void start(){
        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                log.info(clientSocket.toString());
            } catch (IOException e) {
                log.info(e.getMessage());
            }
            clients.add(clientSocket);
            //readers.add(new BufferedReader(new InputStreamReader(clientSocket.getInputStream())));
            //writers.add(new PrintWriter(clientSocket.getOutputStream()));
        }
    }

    public void stop(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            log.info(e.getMessage());
        }finally {
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
