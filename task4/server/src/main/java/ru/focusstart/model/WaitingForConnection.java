package ru.focusstart.model;

import ru.focusstart.connection.ConnectionParameter;
import ru.focusstart.connection.ConnectionParameterBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class WaitingForConnection implements Runnable {
    ServerSocket serverSocket;
    BlockingQueue<ConnectionParameter> processConnections;

    public WaitingForConnection(ServerSocket serverSocket, BlockingQueue<ConnectionParameter> processConnections) {
        this.serverSocket = serverSocket;
        this.processConnections = processConnections;
    }

    @Override
    public void run() {
        Socket clientSocket;
        try {
            while (!Thread.interrupted()) {
                clientSocket = serverSocket.accept();
                ConnectionParameter connectionParameter = new ConnectionParameterBuilder()
                        .buildSocket(clientSocket)
                        .buildReader(new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
                        .buildWriter(new PrintWriter(clientSocket.getOutputStream())).build();
                this.processConnections.put(connectionParameter);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
