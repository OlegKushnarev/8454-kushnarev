package ru.focusstart.view;

import ru.focusstart.controller.LoginListener;
import ru.focusstart.controller.SendListner;

import javax.swing.*;
import java.awt.*;
import java.nio.charset.StandardCharsets;

public interface ButtonCreater {
    JButton getButton();
}

class EnterButtonCreater implements ButtonCreater {

    @Override
    public JButton getButton() {
        JButton enterButton = new JButton(new String("Войти в чат :".getBytes(), StandardCharsets.UTF_8));
        enterButton.addActionListener(new LoginListener());
        return enterButton;
    }
}

class SendButtonCreater implements ButtonCreater {

    @Override
    public JButton getButton() {
        JButton sendButton = new JButton(new String("Отправить".getBytes(), StandardCharsets.UTF_8));
        sendButton.setPreferredSize(new Dimension(110, 75));
        sendButton.addActionListener(new SendListner());
 /*       sendButton.addActionListener(e -> {
            MainWindow mainFrame = (MainWindow) sendButton.getTopLevelAncestor();
            JTextArea messageArea = mainFrame.getMessageArea();
            String message = messageArea.getText();
            if (!message.isEmpty()) {
                messageArea.setText(null);

                //mainFrame.getChatArea().append(message);
            }
        });*/
        return sendButton;
    }
}
