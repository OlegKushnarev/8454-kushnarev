package ru.focusstart.controller;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import ru.focusstart.login.Login;
import ru.focusstart.reader.PropertieReader;
import ru.focusstart.view.Window;
import ru.focusstart.view.WindowCreater;
import ru.focusstart.view.Windows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Facade3 implements InvalidationListener {
    private static Window connectWindow;
    @Override
    public void invalidated(Observable observable) {
        if (connectWindow == null) {
          /*  List<String> enterOptions = new ArrayList<>();
            enterOptions.add("localhost:1111");
            enterOptions.add("Oleg");
            PropertieReader loginReader = new PropertieReader(Login.class, "/login.properties");
            Login login = null;
            try {
               login = new Login(loginReader.read("server.address"),
                        Integer.parseInt(loginReader.read("server.port")),
                        loginReader.read("nickname"));
            } catch (IOException e) {
                e.getMessage();
            }*/

            WindowCreater windowCreater = Windows.CONNECT.getWindowCreater();
            Window connectWindow = windowCreater.createWindow();
            connectWindow.setVisible(true);
        } else {
            connectWindow.dispose();
        }
    }
}
