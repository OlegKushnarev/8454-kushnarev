package ru.focusstart.model;

import ru.focusstart.contactlist.ContactList;
import ru.focusstart.jsonobject.JSONObject;
import ru.focusstart.message.Message;
import ru.focusstart.message.ServiceMessage;
import ru.focusstart.view.MainWindow;
import ru.focusstart.view.Window;

import javax.swing.*;

public interface JSONViewer {
    void view(JSONObject jsonObject);
}

class MessageViewer implements JSONViewer {

    @Override
    public void view(JSONObject jsonObject) {
        Window window = MainWindow.getInstance();
        if (window instanceof MainWindow && jsonObject instanceof Message) {
            MainWindow mainWindow = (MainWindow) window;
            Message message = (Message) jsonObject;
            mainWindow.getMessageArea().append(message + System.lineSeparator());
        }
    }
}

class ServiceMessageViewer implements JSONViewer {

    @Override
    public void view(JSONObject jsonObject) {

        if (jsonObject instanceof ServiceMessage) {
            ServiceMessage serviceMessage = (ServiceMessage) jsonObject;
            JOptionPane.showMessageDialog(new JFrame(),
                    serviceMessage,
                    "Ошибка подключения",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

class ContactListMessageViewer implements JSONViewer {

    @Override
    public void view(JSONObject jsonObject) {
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