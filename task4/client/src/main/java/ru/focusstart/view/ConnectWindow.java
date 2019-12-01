package ru.focusstart.view;

import ru.focusstart.controller.Cheсk;
import ru.focusstart.model.WindowCreater;
import ru.focusstart.model.Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectWindow extends Window implements ActionListener {
    private JTextField serverAddressField;
    private JTextField login;

    public ConnectWindow(int width, int height) {
        super("Подключение", width, height);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel serverAddressLabel = new JLabel("Адрес сервера :");
        /*JTextField */
        serverAddressField = new JTextField(15);
        JLabel loginLabel = new JLabel("Ваш логин :");
        /*JTextField */
        login = new JTextField(15);

        JPanel jPanel = new JPanel();

        jPanel.add(serverAddressLabel);
        jPanel.add(serverAddressField);
        jPanel.add(loginLabel);
        jPanel.add(login);

        JPanel buttonPane = new JPanel();
        JButton enterButton = new JButton("Войти в чат");
        enterButton.addActionListener(this);


        buttonPane.add(enterButton);
        this.add(jPanel, BorderLayout.CENTER);
        this.add(buttonPane, BorderLayout.SOUTH);

        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            String serverAddress = serverAddressField.getText();
            if (serverAddress.isEmpty()) {
                throw new IllegalArgumentException("Не введён адрес сервера!");
            }

            String nickName = login.getText();
            if (nickName.isEmpty()) {
                throw new IllegalArgumentException("Не введён ник!");
            }

            WindowCreater windowCreater = Windows.MAIN.getWindowCreater();
            Window connectWindow = windowCreater.createWindow();
            connectWindow.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(),
                    e.getMessage(),
                    "Ошибка подключения",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public boolean chek(String checkString) {
        return false;
    }
}
