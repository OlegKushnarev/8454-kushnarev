package ru.focusstart;

import ru.focusstart.command.StartCommand;
import ru.focusstart.command.StopCommand;
import ru.focusstart.model.ServerModel;
import ru.focusstart.servermanager.ServerManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerMain {
    public static void main(String[] args) {
        ServerModel chatServer = ServerModel.getInstance();
        ServerManager serverManager = new ServerManager(new StartCommand(chatServer), new StopCommand(chatServer));
        serverManager.ServerStart();
        System.out.println("Сервер запушен.");
        System.out.println("Для остановки сервера введите server stop");

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                if (consoleReader.ready()) {
                    String command = consoleReader.readLine();
                    if (command.equals("server stop")) {
                        serverManager.ServerStop();
                        System.out.println("Сервер остановлен");
                        break;
                    }
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
}
