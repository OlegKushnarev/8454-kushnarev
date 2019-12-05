package ru.focusstart.controller;

import ru.focusstart.view.WindowCreater;
import ru.focusstart.view.Windows;
import ru.focusstart.view.Window;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Facade1 implements Observer {
    private static Window connectWindow;

    @Override
    public void update(Observable observable, Object o) {
        if (connectWindow == null) {
            List<String> enterOptions = new ArrayList<>();
            enterOptions.add("server.ru");
            enterOptions.add("Oleg");
            WindowCreater windowCreater = Windows.CONNECT.getWindowCreater();
            /*Window */connectWindow = windowCreater.createWindow(enterOptions);
            connectWindow.setVisible(true);
        }
        else {
            connectWindow.dispose();
        }
    }
}
