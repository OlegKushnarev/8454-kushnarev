package ru.focusstart.view.textarea;

import javax.swing.*;

public interface TextAreaCreater {
    JTextArea getJTextArea();
}

class ChatAreaCreater implements TextAreaCreater {

    @Override
    public JTextArea getJTextArea() {
        JTextArea chatArea = new JTextArea(20, 56);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setEditable(false);
        return chatArea;
    }
}

class ContactAreaCreater implements TextAreaCreater {

    @Override
    public JTextArea getJTextArea() {
        JTextArea contactArea = new JTextArea(20, 15);
        contactArea.setEditable(false);
        return contactArea;
    }
}

class MessageAreaCreater implements TextAreaCreater {

    @Override
    public JTextArea getJTextArea() {
        JTextArea messageArea = new JTextArea(5, 61);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        return messageArea;
    }
}
