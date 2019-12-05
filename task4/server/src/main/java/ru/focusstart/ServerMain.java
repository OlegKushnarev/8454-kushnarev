package ru.focusstart;

import ru.focusstart.model.ServerModel;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) {
        ServerModel chatServer = ServerModel.getInstance();
        chatServer.start();
        chatServer.stop();
    }
}
