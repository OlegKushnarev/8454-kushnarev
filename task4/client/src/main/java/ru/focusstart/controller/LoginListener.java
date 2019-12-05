package ru.focusstart.controller;

import ru.focusstart.model.ChatModel;
import ru.focusstart.view.ConnectWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            Object object = actionEvent.getSource();
            if (!(object instanceof JButton)) {
                return;
            }
            JButton enterButton = (JButton) object;
            Component parentComp = enterButton.getTopLevelAncestor();
            if (!(parentComp instanceof ConnectWindow)) {
                return;
            }

            ConnectWindow connectWindow = (ConnectWindow) parentComp;
            JTextField serverAddressField = connectWindow.getServerAddressField();
            String serverAddress = serverAddressField.getText();
            if (serverAddress.isEmpty()) {
                throw new IllegalArgumentException("Не введён адрес сервера!");
            }
            JTextField login = connectWindow.getLogin();
            String nickName = login.getText();
            if (nickName.isEmpty()) {
                throw new IllegalArgumentException("Не введён ник!");
            }

            ChatModel chatClient = ChatModel.getInstance();
            chatClient.setServerAddress(serverAddress);
            chatClient.setUserNickname(nickName);
            //connectWindow.dispose();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(new JFrame(),
                    ex.getMessage(),
                    "Ошибка подключения",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
