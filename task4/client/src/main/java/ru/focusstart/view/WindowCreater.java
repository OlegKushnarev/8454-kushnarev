package ru.focusstart.view;

public interface WindowCreater {
    Window createWindow();
}

class MainWindowCreater implements WindowCreater {

    @Override
    public MainWindow createWindow() {
        return new MainWindow(800, 500/*, options*/);
    }
}

class ConnectWindowCreater implements WindowCreater {

    @Override
    public Window createWindow() {
        return new ConnectWindow(300, 150);
    }
}