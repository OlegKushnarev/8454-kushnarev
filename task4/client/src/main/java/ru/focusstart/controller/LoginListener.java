package ru.focusstart.controller;

import ru.focusstart.model.WindowCreater;
import ru.focusstart.model.Windows;
import ru.focusstart.view.ConnectWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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

         /*   connectWindow.dispose();

            WindowCreater windowCreater = Windows.MAIN.getWindowCreater();
            List<String> nickNames = new ArrayList<>();
            nickNames.add(nickName);
            Window maintWindow = windowCreater.createWindow(nickNames);
            maintWindow.setVisible(true);*/
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(new JFrame(),
                    ex.getMessage(),
                    "Ошибка подключения",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
