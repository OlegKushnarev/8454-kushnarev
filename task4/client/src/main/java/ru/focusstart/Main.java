package ru.focusstart;

import ru.focusstart.controller.Facade1;
import ru.focusstart.controller.Facade2;
import ru.focusstart.model.ChatModel;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ChatModel chatClient = ChatModel.getInstance();
        chatClient.addObserver(new Facade1());
        chatClient.enterToChat();

        while (!chatClient.isConnect()) {
            while (chatClient.getServerAddress().isEmpty() &&
                    chatClient.getUserNickname().isEmpty()) {
              //  System.out.println("Пока не нажал кнопку");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.getMessage();
                }
            }
            // System.out.println("Прошли цикл!!!");
            try {
                chatClient.addObserver(new Facade2());
                chatClient.connectToServer();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(new JFrame(),
                        e.getMessage(),
                        "Ошибка подключения",
                        JOptionPane.ERROR_MESSAGE);
            }finally {
                chatClient.setServerAddress("");
                chatClient.setUserNickname("");
            }
        }
    }
}