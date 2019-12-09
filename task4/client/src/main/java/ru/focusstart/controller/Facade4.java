package ru.focusstart.controller;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import ru.focusstart.view.Window;
import ru.focusstart.view.WindowCreater;
import ru.focusstart.view.Windows;

public class Facade4 implements InvalidationListener {

    @Override
    public void invalidated(Observable observable) {
      /*  if (observable instanceof ChatModel) {
            WindowCreater windowCreater = Windows.MAIN.getWindowCreater();
            ChatModel chatClient = ChatModel.getInstance();
            Window mainWindow = windowCreater.createWindow(chatClient.getNickNames());
            mainWindow.setVisible(true);
        }*/

        WindowCreater windowCreater = Windows.MAIN.getWindowCreater();
        Window mainWindow = windowCreater.createWindow();
        mainWindow.setVisible(true);
    }
}
