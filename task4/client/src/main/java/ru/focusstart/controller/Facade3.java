package ru.focusstart.controller;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import ru.focusstart.view.Window;
import ru.focusstart.view.WindowCreater;
import ru.focusstart.view.Windows;

public class Facade3 implements InvalidationListener {
    private static Window connectWindow;
    @Override
    public void invalidated(Observable observable) {
        if (connectWindow == null) {
            WindowCreater windowCreater = Windows.CONNECT.getWindowCreater();
            connectWindow = windowCreater.createWindow();
            connectWindow.setVisible(true);
        } else {
            connectWindow.dispose();
            connectWindow = null;
        }
    }
}
