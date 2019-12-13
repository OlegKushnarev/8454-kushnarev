package ru.focusstart.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.focusstart.view.window.MainWindow;
import ru.focusstart.view.window.Window;
import ru.focusstart.view.window.WindowCreater;
import ru.focusstart.view.window.Windows;

public class Facade4 implements ChangeListener<Boolean> {
    private static Window mainWindow;

    @Override
    public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
        if (t1) {
            WindowCreater windowCreater = Windows.MAIN.getWindowCreater();
            mainWindow = windowCreater.createWindow();
            MainWindow.setInstance(mainWindow);
            mainWindow.setVisible(true);
        } else {
            mainWindow.dispose();
            MainWindow.setInstance(null);
            mainWindow = null;
        }
    }
}
