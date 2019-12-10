package ru.focusstart.controller;

import ru.focusstart.model.ChatModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ChatModel chatClient = ChatModel.getInstance();
        chatClient.closeClient();
    }
}