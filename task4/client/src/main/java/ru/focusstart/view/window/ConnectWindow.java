package ru.focusstart.view.window;

import ru.focusstart.view.button.Buttons;

import javax.swing.*;
import java.awt.*;

public class ConnectWindow extends Window {
    private JTextField serverAddressField;
    private JTextField nicknameField;

    public ConnectWindow(int width, int height) {
        super("Connect", width, height);

        JLabel serverAddressLabel = new JLabel("Server address and port: ");
        serverAddressLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        serverAddressField = new JTextField(15);
        serverAddressField.setAlignmentX(Component.LEFT_ALIGNMENT);
        serverAddressField.setToolTipText("Enter server address and port");
        JLabel loginLabel = new JLabel("Your nickname: ");
        loginLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        nicknameField = new JTextField(15);
        nicknameField.setToolTipText("Enter your nickname");
        nicknameField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel textFieldPane = new JPanel();
        textFieldPane.setLayout(new BoxLayout(textFieldPane, BoxLayout.Y_AXIS));
        textFieldPane.add(serverAddressField);
        textFieldPane.add(nicknameField);

        JPanel labelPane = new JPanel();
        labelPane.setLayout(new BoxLayout(labelPane, BoxLayout.Y_AXIS));
        labelPane.add(serverAddressLabel);
        labelPane.add(loginLabel);

        JPanel buttonPane = new JPanel();
        JButton enterButton = Buttons.ENTER.getButtonCreater().getButton();
        buttonPane.add(enterButton, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.add(labelPane, BorderLayout.WEST);
        panel.add(textFieldPane, BorderLayout.EAST);
        panel.add(buttonPane, BorderLayout.SOUTH);
        this.add(panel, BorderLayout.CENTER);

    }

    public JTextField getServerAddressField() {
        return serverAddressField;
    }

    public JTextField getNicknameField() {
        return nicknameField;
    }
}
