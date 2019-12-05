package ru.focusstart.view;

import java.util.List;

public interface WindowCreater {
    Window createWindow();
    Window createWindow(List<String> options);
}

class MainWindowCreater implements WindowCreater {

    @Override
    public Window createWindow() {
        return null;
    }

    @Override
    public MainWindow createWindow(List<String> options) {
        if (options.isEmpty()) {
            throw new IllegalArgumentException("Список никнеймов пуст");
        }
        return new MainWindow(800, 500, options);
    }
}

class ConnectWindowCreater implements WindowCreater {

    @Override
    public Window createWindow() {
        return new ConnectWindow(300, 150);
    }

    @Override
    public ConnectWindow createWindow(List<String> options) {
        if (options.isEmpty()) {
            throw new IllegalArgumentException("Список параметров пуст");
        }
        return new ConnectWindow(300, 150, options.get(0), options.get(1));
    }
}