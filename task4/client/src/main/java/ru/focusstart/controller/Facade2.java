package ru.focusstart.controller;

import ru.focusstart.model.ChatModel;
import ru.focusstart.view.Window;
import ru.focusstart.view.WindowCreater;
import ru.focusstart.view.Windows;
import java.util.Observable;
import java.util.Observer;

public class Facade2 implements Observer {
    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof ChatModel)
        {
            WindowCreater windowCreater = Windows.MAIN.getWindowCreater();
            ChatModel chatClient = (ChatModel) observable;
            Window maintWindow = windowCreater.createWindow(chatClient.getNickNames());
            maintWindow.setVisible(true);
        }
    }
}
