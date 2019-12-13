package ru.focusstart.view.window;

public interface WindowCreater {
    Window createWindow();
}

class MainWindowCreater implements WindowCreater {

    @Override
    public MainWindow createWindow() {
        return new MainWindow(800, 500);
    }
}

class ConnectWindowCreater implements WindowCreater {

    @Override
    public Window createWindow() {
        return new ConnectWindow(300, 150);
    }
}