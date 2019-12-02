package ru.focusstart.view;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends Window {

    public MainWindow(int width, int height) {
        super("Чат", width, height);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);

        JTextArea chatArea = new JTextArea(20, 56/*width - 50, height - 5*/);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setEditable(false);
        this.add(new JScrollPane(chatArea), BorderLayout.WEST);

        JTextArea contactArea = new JTextArea(20, 15);
        //contactArea.setLineWrap(true);
        ///contactArea.setWrapStyleWord(true);
        contactArea.setEditable(false);
        this.add(new JScrollPane(contactArea), BorderLayout.EAST);

        JTextArea messageArea = new JTextArea(5, 15/*width - 50, height - 5*/);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        this.add(new JScrollPane(messageArea), BorderLayout.SOUTH);
        this.revalidate();

        JMenu jMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        jMenu.add(exitItem);

        JMenu jMenuConnect = new JMenu("Connection");
        JMenuItem connect = new JMenuItem("Connect");
        //connect.addActionListener(e -> new ConnectWindow(this, new Rectangle(this.getX() + this.getWidth() / 2 - 200, this.getY() + this.getHeight() / 2 - 150, 400, 300))/*Windows.CONNECT.getWindowCreater().createWindow().setVisible(true)*/);
        jMenuConnect.add(connect);

        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);
        jMenuBar.add(jMenuConnect);

        this.setJMenuBar(jMenuBar);
    }

    @Override
    public boolean chek(String checkString) {
        return false;
    }
}
