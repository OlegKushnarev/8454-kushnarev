package ru.focusstart.view;

import ru.focusstart.model.TextAreas;
import ru.focusstart.model.WindowCreater;
import ru.focusstart.model.Windows;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends Window {

    public MainWindow(int width, int height, List<String> nickNames) {
        super("Чат", width, height);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);

        JTextArea chatArea = TextAreas.CHATAREA.getTextAreaCreater().getJTextArea();
        this.add(new JScrollPane(chatArea), BorderLayout.WEST);

        JTextArea contactArea = TextAreas.CONTACTAREA.getTextAreaCreater().getJTextArea();
        for (String nickName :
                nickNames) {
            contactArea.setText(nickName);
        }
        this.add(new JScrollPane(contactArea), BorderLayout.EAST);

        JTextArea messageArea = TextAreas.MESSAGEAREA.getTextAreaCreater().getJTextArea();
        JPanel jPanel = new JPanel();
        jPanel.add(new JScrollPane(messageArea));

        JButton sendButton = new JButton("Отправить");
        sendButton.setPreferredSize(new Dimension(110, 75));
        sendButton.addActionListener(e -> {
            String message = messageArea.getText();
            if (!message.isEmpty()) {
                messageArea.setText(null);
                chatArea.setText(message);
            }
        });
        jPanel.add(sendButton);

        this.add(jPanel, BorderLayout.SOUTH);
        //this.revalidate();

        JMenu jMenu = new JMenu("Файл");
        JMenuItem logOut = new JMenuItem("Выйти из чата");
        logOut.addActionListener(e -> {
            this.dispose();
            List<String> enterOptions = new ArrayList<>();
            enterOptions.add("server.ru");
            enterOptions.add("Oleg");
            WindowCreater windowCreater = Windows.CONNECT.getWindowCreater();
            Window connectWindow = windowCreater.createWindow(enterOptions);
            connectWindow.setVisible(true);
        });
        jMenu.add(logOut);
        JMenuItem exitItem = new JMenuItem("Закрыть");
        exitItem.addActionListener(e -> System.exit(0));
        jMenu.add(exitItem);

        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);

        this.setJMenuBar(jMenuBar);
    }

    @Override
    public boolean chek(String checkString) {
        return false;
    }
}
