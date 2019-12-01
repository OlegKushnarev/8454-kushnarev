package ru.focusstart.model;

import ru.focusstart.view.ConnectWindow;
import ru.focusstart.view.MainWindow;
import ru.focusstart.view.Window;

import java.awt.*;

public interface WindowCreater {
    Window createWindow();
}

class MainWindowCreater implements WindowCreater {

    @Override
    public MainWindow createWindow() {
      //  Toolkit toolkit = Toolkit.getDefaultToolkit();
      //  Dimension screenSize = toolkit.getScreenSize();
        return new MainWindow(800, 500);
    }
}

class ConnectWindowCreater implements WindowCreater {

    @Override
    public ConnectWindow createWindow() {
        //Toolkit toolkit = Toolkit.getDefaultToolkit();
       /// Dimension screenSize = toolkit.getScreenSize();
        return new ConnectWindow(300, 150);
    }
}