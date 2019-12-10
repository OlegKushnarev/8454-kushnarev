package ru.focusstart.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.focusstart.view.Window;
import ru.focusstart.view.WindowCreater;
import ru.focusstart.view.Windows;

public class Facade3 implements ChangeListener<Boolean> {
    private static Window connectWindow;

    @Override
    public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
        if (t1) {
            WindowCreater windowCreater = Windows.CONNECT.getWindowCreater();
            connectWindow = windowCreater.createWindow();
            connectWindow.setVisible(true);
        } else {
            connectWindow.dispose();
            connectWindow = null;
        }
    }
}