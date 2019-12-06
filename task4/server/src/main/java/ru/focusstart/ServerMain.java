package ru.focusstart;

import ru.focusstart.model.ServerModel;

public class ServerMain {
    public static void main(String[] args) {
        ServerModel chatServer = ServerModel.getInstance();
        chatServer.start();
        chatServer.watch();
    }
}
