package ru.focusstart.model;

import ru.focusstart.ChatClient;
import ru.focusstart.controller.LoginListener;
import ru.focusstart.view.Window;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
      /* List<String> enterOptions = new ArrayList<>();
        enterOptions.add("server.ru");
        enterOptions.add("Oleg");
        WindowCreater windowCreater = Windows.CONNECT.getWindowCreater();
        Window connectWindow = windowCreater.createWindow(enterOptions);
        connectWindow.setVisible(true);*/
        ChatClient chatClient = new ChatClient();
        chatClient.enterToChat(e->{
            new LoginListener();
            if (!chatClient.getEnterOptions().isEmpty()) {
                chatClient.startChat();
            }
        });

    }
}
