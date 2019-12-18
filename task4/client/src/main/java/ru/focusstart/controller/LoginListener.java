package ru.focusstart.controller;

import ru.focusstart.login.Login;
import ru.focusstart.model.ChatModel;
import ru.focusstart.view.window.ConnectWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
            String[] serverAddressParts = serverAddress.split(":");
            if (serverAddressParts.length == 0) {
                throw new IllegalArgumentException("Server address not entered!");
            }
            if (serverAddressParts.length < 2) {
                throw new IllegalArgumentException("The port for connecting to the server is not entered!");
            }
            JTextField login = connectWindow.getNicknameField();
            String nickName = login.getText();
            if (nickName.isEmpty()) {
                throw new IllegalArgumentException("Nickname not entered!");
            }

            ChatModel chatClient = ChatModel.getInstance();
            chatClient.setNickname(nickName);
            chatClient.connectToServer(new Login(serverAddressParts[0], Integer.parseInt(serverAddressParts[1]), nickName));
        } catch (IllegalArgumentException | IOException e) {
            JOptionPane.showMessageDialog(new JFrame(),
                    e.getMessage(),
                    "Connection error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
