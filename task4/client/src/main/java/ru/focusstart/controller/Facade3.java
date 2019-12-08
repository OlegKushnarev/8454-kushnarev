package ru.focusstart.controller;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import ru.focusstart.view.Window;
import ru.focusstart.view.WindowCreater;
import ru.focusstart.view.Windows;

import java.util.ArrayList;
import java.util.List;

public class Facade3 implements InvalidationListener {
    @Override
    public void invalidated(Observable observable) {
        List<String> enterOptions = new ArrayList<>();
        enterOptions.add("localhost:1111");
        enterOptions.add("Oleg");
        WindowCreater windowCreater = Windows.CONNECT.getWindowCreater();
        Window connectWindow = windowCreater.createWindow(enterOptions);
        connectWindow.setVisible(true);
    }
}
