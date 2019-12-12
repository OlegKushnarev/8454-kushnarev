package ru.focusstart.model;

import ru.focusstart.contactlist.ContactList;
import ru.focusstart.jsonobject.JSONObject;
import ru.focusstart.message.Message;
import ru.focusstart.message.NicknameRejectedServiceMessage;
import ru.focusstart.view.MainWindow;
import ru.focusstart.view.Window;

import javax.swing.*;

public interface JSONHandler {
    void view(JSONObject jsonObject);
}

class MessageHandler implements JSONHandler {

    @Override
    public void view(JSONObject jsonObject) {
        if (jsonObject != null) {
            Window window = MainWindow.getInstance();
            if (window instanceof MainWindow && jsonObject instanceof Message) {
                MainWindow mainWindow = (MainWindow) window;
                Message message = (Message) jsonObject;
                mainWindow.getMessageArea().append(message + System.lineSeparator());
            }
        }
    }
}

class ContactListMessageHandler implements JSONHandler {

    @Override
    public void view(JSONObject jsonObject) {
        if (jsonObject != null) {
            Window window = MainWindow.getInstance();
            if (window instanceof MainWindow && jsonObject instanceof ContactList) {
                MainWindow mainWindow = (MainWindow) window;
                ContactList contactList = (ContactList) jsonObject;
                JTextArea contactArea = mainWindow.getContactArea();
                contactArea.setText("");
                for (String nickname :
                        contactList) {
                    contactArea.append(nickname + System.lineSeparator());
                }
            }
        }
    }
}

class NicknameAcceptedServiceMessageHandler implements JSONHandler {

    @Override
    public void view(JSONObject jsonObject) {
        if (jsonObject != null) {
            ChatModel chatClient = ChatModel.getInstance();
            chatClient.isOnline();
        }
    }
}

class NicknameRejectedServiceMessageHandler implements JSONHandler {

    @Override
    public void view(JSONObject jsonObject) {
        if (jsonObject != null) {
            if (jsonObject instanceof NicknameRejectedServiceMessage) {
                //ServiceMessage serviceMessage = (ServiceMessage) jsonObject;
                JOptionPane.showMessageDialog(new JFrame(),
                        "Nickname already taken",
                        "Connection error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

class MessageDeliveredServiceMessageHandler implements JSONHandler {

    @Override
    public void view(JSONObject jsonObject) {
        if (jsonObject != null) {
            Window window = MainWindow.getInstance();
            if (window instanceof MainWindow && jsonObject instanceof Message) {
                MainWindow mainWindow = (MainWindow) window;
                Message message = (Message) jsonObject;
                mainWindow.getMessageArea().append(message + System.lineSeparator());
            }
        }
    }
}