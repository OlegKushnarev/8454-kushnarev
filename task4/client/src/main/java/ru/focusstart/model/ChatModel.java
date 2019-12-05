package ru.focusstart.model;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ChatModel extends Observable {
    private static ChatModel instance;
    boolean OnEnter;
    boolean isConnect;
    private String serverAddress;
    private String userNickname;
    List<String> nickNames;

// Window currentWindow;
    // Window mainWindow;

    public static ChatModel getInstance() {
        if (instance == null) {
            instance = new ChatModel();
        }
        return instance;
    }

    private ChatModel() {
        super();
        this.OnEnter = false;
        this.isConnect = false;
        this.serverAddress = "";
        this.userNickname = "";
        this.nickNames = new ArrayList<>();
    }

    public boolean isConnect() {
        return isConnect;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public List<String> getNickNames() {
        return nickNames;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public void enterToChat() {
        OnEnter = true;
        setChanged();
        notifyObservers();
    }

    public void connectToServer() throws IOException {
        if (serverAddress.isEmpty()) {
            throw new IllegalArgumentException("Не задан адрес сервера!");
        }

        if (userNickname.isEmpty()) {
            throw new IllegalArgumentException("Не задан ник!");
        }

        Socket socket = new Socket(serverAddress, 1111);
        nickNames.add(userNickname);
        setChanged();
        notifyObservers();
    }
}
