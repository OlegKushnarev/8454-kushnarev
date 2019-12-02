package ru.focusstart.model;

import ru.focusstart.view.ConnectWindow;
import ru.focusstart.view.MainWindow;
import ru.focusstart.view.Window;

import java.awt.*;
import java.util.Map;

public interface WindowCreater {
    Window createWindow(Map<String, String> options);
}

class MainWindowCreater implements WindowCreater {

    @Override
    public MainWindow createWindow(Map<String, String> options) {
        if (options.isEmpty()) {
            throw new IllegalArgumentException("Карта настроек пуста!");
        }

        String nickName = options.get("Nickname");
        if (nickName.isEmpty()) {
            throw new IllegalArgumentException("Никнейм не задан!");
        }

        return new MainWindow(800, 500);
    }
}

class ConnectWindowCreater implements WindowCreater {

    @Override
    public ConnectWindow createWindow(Map<String, String> options) {
        if (options.isEmpty()) {
            return new ConnectWindow(300, 150);
        }
        else {
            return new ConnectWindow(300, 150);
        }
    }
}