package ru.focusstart.controller;

import ru.focusstart.view.window.Window;

import java.util.Observable;
import java.util.Observer;

public class Facade2 implements Observer {
    private static Window mainWindow;

    @Override
    public void update(Observable observable, Object o) {/*
        if (mainWindow == null) {
            if (observable instanceof ChatModel) {
                WindowCreater windowCreater = Windows.MAIN.getWindowCreater();
                ChatModel chatClient = (ChatModel) observable;
                mainWindow = windowCreater.createWindow(chatClient.getNickNames());
                mainWindow.setVisible(true);
            }
        } else {
            if (observable instanceof ChatModel) {
                ChatModel chatClient = (ChatModel) observable;
                MainWindow window = (MainWindow) mainWindow;
                //System.out.println("Печатаю сообщение отсервера");
                window.getChatArea().append(chatClient.getMessageFromServer() + System.lineSeparator());
                JTextArea contacts = window.getContactArea();
                for (String nickname :
                        chatClient.getNickNames()) {
                    contacts.append(nickname);
                }
            }
        }*/
    }
}
