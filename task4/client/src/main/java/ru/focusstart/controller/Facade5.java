package ru.focusstart.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.focusstart.view.MainWindow;

public class Facade5 implements ChangeListener<String> {
    private static MainWindow mainWindow;

    @Override
    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
        if (mainWindow == null) {
            mainWindow = MainWindow.getInstance();
            mainWindow.getMessageArea().append(t1 + System.lineSeparator());
        }
    }
}