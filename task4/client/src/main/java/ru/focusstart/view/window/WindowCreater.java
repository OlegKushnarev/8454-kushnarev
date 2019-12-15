package ru.focusstart.view.window;

import ru.focusstart.controller.CloseListener;

import javax.swing.*;

public interface WindowCreater {
    Window createWindow();
}

class MainWindowCreater implements WindowCreater {

    @Override
    public MainWindow createWindow() {
        MainWindow mainWindow = new MainWindow(800, 500);
        mainWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        mainWindow.addWindowListener(new CloseListener());
        return mainWindow;
    }
}

class ConnectWindowCreater implements WindowCreater {

    @Override
    public Window createWindow() {
        ConnectWindow connectWindow = new ConnectWindow(350, 150);
        connectWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //connectWindow.addWindowListener(new CloseListener());
        return connectWindow;
    }
}