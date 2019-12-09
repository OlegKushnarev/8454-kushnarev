package ru.focusstart.view;

import javax.swing.*;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends Window {
    private  JTextArea chatArea;
    private JTextArea messageArea;
    private JTextArea contactArea;

    public MainWindow(int width, int height/*, List<String> nickNames*/) {
        //super("Чат", width, height);
        super(new String("Чат".getBytes(), StandardCharsets.UTF_8), width, height);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        chatArea = TextAreas.CHATAREA.getTextAreaCreater().getJTextArea();
        this.add(new JScrollPane(chatArea), BorderLayout.WEST);

        contactArea = TextAreas.CONTACTAREA.getTextAreaCreater().getJTextArea();
     /*   for (String nickName :
                nickNames) {
            contactArea.setText(nickName);
        }*/
        this.add(new JScrollPane(contactArea), BorderLayout.EAST);

        messageArea = TextAreas.MESSAGEAREA.getTextAreaCreater().getJTextArea();
        JPanel jPanel = new JPanel();
        jPanel.add(new JScrollPane(messageArea));

        JButton sendButton = Buttons.SEND.getButtonCreater().getButton();
        jPanel.add(sendButton);

        this.add(jPanel, BorderLayout.SOUTH);

        JMenuItem logOut = new JMenuItem(new String("Выйти из чата".getBytes(), StandardCharsets.UTF_8));
        logOut.addActionListener(e -> {
            this.dispose();
         /*   List<String> enterOptions = new ArrayList<>();
            enterOptions.add("server.ru");
            enterOptions.add("Oleg");*/
            WindowCreater windowCreater = Windows.CONNECT.getWindowCreater();
            Window connectWindow = windowCreater.createWindow();
            connectWindow.setVisible(true);
        });

        JMenuItem exitItem = new JMenuItem(new String("Закрыть".getBytes(), StandardCharsets.UTF_8));
        exitItem.addActionListener(e -> System.exit(0));

        JMenu jMenu = new JMenu(new String("Файл".getBytes(), StandardCharsets.UTF_8));
        jMenu.add(logOut);
        jMenu.add(exitItem);
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);
        this.setJMenuBar(jMenuBar);
    }

    public JTextArea getChatArea() {
        return chatArea;
    }

    public JTextArea getMessageArea() {
        return messageArea;
    }

    public JTextArea getContactArea() {
        return contactArea;
    }
}
