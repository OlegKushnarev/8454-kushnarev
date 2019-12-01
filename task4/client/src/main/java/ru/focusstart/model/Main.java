package ru.focusstart.model;

import ru.focusstart.view.Window;

public class Main {
    public static void main(String[] args) {
        WindowCreater windowCreater = Windows.CONNECT.getWindowCreater();
        Window connectWindow = windowCreater.createWindow();
        connectWindow.setVisible(true);
    }
}
