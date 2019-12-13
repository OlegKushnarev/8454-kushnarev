package ru.focusstart.view.window;

import ru.focusstart.view.button.Buttons;

import javax.swing.*;
import java.awt.*;

public class ConnectWindow extends Window {
    private JTextField serverAddressField;
    private JTextField nicknameField;

    public ConnectWindow(int width, int height) {
        super("Connect", width, height);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel serverAddressLabel = new JLabel("Server address and port: ");
        serverAddressField = new JTextField(15);
        JLabel loginLabel = new JLabel("Your nickname: ");
        nicknameField = new JTextField(15);

        serverAddressField.setToolTipText("Enter server address and port");
        nicknameField.setToolTipText("Enter your nickname");

        JPanel jPanel = new JPanel();
        jPanel.add(serverAddressLabel);
        jPanel.add(serverAddressField);
        jPanel.add(loginLabel);
        jPanel.add(nicknameField);

        JPanel buttonPane = new JPanel();
        JButton enterButton = Buttons.ENTER.getButtonCreater().getButton();
        buttonPane.add(enterButton);

        this.add(jPanel, BorderLayout.CENTER);
        this.add(buttonPane, BorderLayout.SOUTH);
    }

    public JTextField getServerAddressField() {
        return serverAddressField;
    }

    public JTextField getNicknameField() {
        return nicknameField;
    }
}
