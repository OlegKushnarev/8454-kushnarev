package ru.focusstart.controller;

import ru.focusstart.message.Message;
import ru.focusstart.model.ChatModel;
import ru.focusstart.view.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendListner implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object object = actionEvent.getSource();
        if (!(object instanceof JButton)) {
            return;
        }
        JButton enterButton = (JButton) object;
        Component parentComp = enterButton.getTopLevelAncestor();
        if (!(parentComp instanceof MainWindow)) {
            return;
        }

        MainWindow mainWindow = (MainWindow) parentComp;

        JTextArea messageArea = mainWindow.getMessageArea();
        Message message = new Message(messageArea.getText());
        //if (message != null) {
            messageArea.setText(null);
            ChatModel chatClient = ChatModel.getInstance();
            chatClient.setMessageFromUser(message);
            //System.out.println("SendListner " + message);
       // }
    }
}
