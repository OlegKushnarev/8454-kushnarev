package ru.focusstart.controller;

import ru.focusstart.model.ChatModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CloseListener implements ActionListener, WindowListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ChatModel chatClient = ChatModel.getInstance();
        chatClient.exitFromChat();
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        ChatModel chatClient = ChatModel.getInstance();
        chatClient.closeClient();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}