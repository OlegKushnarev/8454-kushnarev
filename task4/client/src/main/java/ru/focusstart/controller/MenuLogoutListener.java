package ru.focusstart.controller;

import ru.focusstart.model.ChatModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuLogoutListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ChatModel chatClient = ChatModel.getInstance();
        chatClient.exitFromChat();
        chatClient.login();
    }
}
