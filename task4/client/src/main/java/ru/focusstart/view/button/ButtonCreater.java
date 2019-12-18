package ru.focusstart.view.button;

import ru.focusstart.controller.LoginListener;
import ru.focusstart.controller.SendListner;

import javax.swing.*;
import java.awt.*;

public interface ButtonCreater {
    JButton getButton();
}

class EnterButtonCreater implements ButtonCreater {

    @Override
    public JButton getButton() {
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new LoginListener());
        return enterButton;
    }
}

class SendButtonCreater implements ButtonCreater {

    @Override
    public JButton getButton() {
        JButton sendButton = new JButton("Send");
        sendButton.setPreferredSize(new Dimension(110, 75));
        sendButton.addActionListener(new SendListner());
        return sendButton;
    }
}
