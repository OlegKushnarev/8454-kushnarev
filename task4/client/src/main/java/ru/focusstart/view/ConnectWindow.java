package ru.focusstart.view;

import ru.focusstart.model.Buttons;
import ru.focusstart.model.WindowCreater;
import ru.focusstart.model.Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ConnectWindow extends Window {
    private JTextField serverAddressField;
    private JTextField login;

    public ConnectWindow(int width, int height) {
        //super("Подключение", width, height);
        super(new String("Подключение".getBytes(), StandardCharsets.UTF_8), width, height);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel serverAddressLabel = new JLabel(new String("Адрес сервера :".getBytes(), StandardCharsets.UTF_8));
        serverAddressField = new JTextField(15);
        JLabel loginLabel = new JLabel(new String("Ваш логин :".getBytes(), StandardCharsets.UTF_8));
        login = new JTextField(15);

        JPanel jPanel = new JPanel();
        jPanel.add(serverAddressLabel);
        jPanel.add(serverAddressField);
        jPanel.add(loginLabel);
        jPanel.add(login);

        JPanel buttonPane = new JPanel();
        JButton enterButton = Buttons.ENTER.getButtonCreater().getButton();
        buttonPane.add(enterButton);

        this.add(jPanel, BorderLayout.CENTER);
        this.add(buttonPane, BorderLayout.SOUTH);
       // this.setResizable(false);
       // this.pack();
        //this.setVisible(true);
    }

    public ConnectWindow(int width, int height, String defaultServerAddress, String defaultNickName) {
        this(width, height);
        serverAddressField.setText(defaultServerAddress);
        login.setText(defaultNickName);
    }

    public JTextField getServerAddressField() {
        return serverAddressField;
    }

    public JTextField getLogin() {
        return login;
    }
}
