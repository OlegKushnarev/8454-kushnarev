package ru.focusstart;

import ru.focusstart.model.WindowCreater;
import ru.focusstart.model.Windows;
import ru.focusstart.view.ConnectWindow;
import ru.focusstart.view.MainWindow;
import ru.focusstart.view.Window;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ChatClient {
    boolean OnEnter;
    List<String> enterOptions;
    Window currentWindow;
   // Window mainWindow;

    public ChatClient() {
        OnEnter = false;
      /*  enterOptions = new ArrayList<>();
        enterOptions.add("server.ru");
        enterOptions.add("Oleg");*/
    }

    public void enterToChat(ActionListener l) {
        OnEnter = true;
     /*   WindowCreater windowCreater = Windows.CONNECT.getWindowCreater();
        currentWindow = windowCreater.createWindow(enterOptions);
        currentWindow.setVisible(true);*/
    }

    public void startChat() {
        connectWindow.dispose();
        WindowCreater windowCreater = Windows.MAIN.getWindowCreater();
        mainWindow = windowCreater.createWindow(enterOptions);
        mainWindow.setVisible(true);
    }
}
