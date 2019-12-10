package ru.focusstart.view;

import ru.focusstart.controller.CloseListener;
import ru.focusstart.controller.LogoutListener;
import ru.focusstart.model.ChatModel;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends Window {
   private static Window currentInstance;
    private JTextArea chatArea;
    private JTextArea messageArea;
    private JTextArea contactArea;

    public static Window getInstance() {
        return currentInstance;
    }

    public static void setInstance(Window instance) {
        currentInstance = instance;
    }

    public MainWindow(int width, int height) {
        //super("Чат", width, height);
        super("Chat", width, height);
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

        JMenuItem logOut = new JMenuItem("Log out");
        logOut.addActionListener(new LogoutListener());
        JMenuItem exitItem = new JMenuItem("Close");
        exitItem.addActionListener(new CloseListener());

        JMenu jMenu = new JMenu("File");
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
