package ru.focusstart.controller;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import ru.focusstart.view.Window;
import ru.focusstart.view.WindowCreater;
import ru.focusstart.view.Windows;

public class Facade4 implements InvalidationListener {
    private static Window mainWindow;

    @Override
    public void invalidated(Observable observable) {
        if (mainWindow == null) {
            WindowCreater windowCreater = Windows.MAIN.getWindowCreater();
            mainWindow = windowCreater.createWindow();
            mainWindow.setVisible(true);
        } else {
            mainWindow.dispose();
            mainWindow = null;
        }
    }
}
