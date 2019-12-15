package ru.focusstart;

import ru.focusstart.model.ServerModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerMain {
    public static void main(String[] args) {
        try {
            ServerModel chatServer = ServerModel.getInstance();
            chatServer.start();
            System.out.println("Сервер запушен.");
            System.out.println("Для остановки сервера введите server stop");
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                // if (consoleReader.ready()) {
                String command = consoleReader.readLine();
                if (command.equals("server stop")) {
                    chatServer.stop();
                    System.out.println("Сервер остановлен");
                    break;
                }
                //  }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
