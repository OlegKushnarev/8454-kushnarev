package ru.focusstart.controller;

import ru.focusstart.model.ChatModel;
import ru.focusstart.view.*;
import ru.focusstart.view.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ChatModel chatClient = ChatModel.getInstance();
        chatClient.exitFromChat();
    }
}
