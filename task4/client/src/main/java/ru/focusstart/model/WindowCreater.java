package ru.focusstart.model;

import ru.focusstart.view.ConnectWindow;
import ru.focusstart.view.MainWindow;
import ru.focusstart.view.Window;
import java.util.List;

public interface WindowCreater {
    Window createWindow(List<String> options);
}

class MainWindowCreater implements WindowCreater {

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
    public ConnectWindow createWindow(List<String> options) {
        if (options.isEmpty()) {
            return new ConnectWindow(300, 150);
        }
        else {
            return new ConnectWindow(300, 150, options.get(0), options.get(1));
        }
    }
}