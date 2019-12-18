package ru.focusstart.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import ru.focusstart.controller.JSONObjectListner;
import ru.focusstart.jsonobject.JSONObject;
import ru.focusstart.login.Login;
import ru.focusstart.model.booleanproperties.BooleanProperties;
import ru.focusstart.serialization.JSONDeserialization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatModel {
    private static ChatModel instance;
    private Socket socket;
    private SimpleBooleanProperty OnEnter;
    private SimpleBooleanProperty isConnect;
    private boolean listenerTreadInterrupte;
    private String nickname;
    private SimpleObjectProperty<JSONObject> jsonObjectSimpleObject;
    BufferedReader reader;
    PrintWriter writer;
    Thread listenerServerThread;

    public static ChatModel getInstance() {
        if (instance == null) {
            instance = new ChatModel();
        }
        return instance;
    }

    private ChatModel() {
        this.OnEnter = BooleanProperties.ON_ENTER.getBooleanPropertiesCreater().getBooleanProperty();
        this.isConnect = BooleanProperties.IS_CONNECT.getBooleanPropertiesCreater().getBooleanProperty();
        this.jsonObjectSimpleObject = new SimpleObjectProperty<>();
        this.jsonObjectSimpleObject.addListener(new JSONObjectListner());
        this.socket = null;
        this.nickname = "";
    }

    public void login() {
        this.OnEnter.set(true);
    }

    public void isOnline() {
        this.OnEnter.set(false);
        this.isConnect.set(true);
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void closeConnection() {
        try {
            if (this.socket != null && this.socket.isConnected()) {
                this.socket.close();
            }
            if (this.reader != null) {
                this.reader.close();
            }
            if (this.writer != null) {
                this.writer.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            this.listenerTreadInterrupte = true;
            try {
                this.listenerServerThread.join();
            } catch (InterruptedException ignored) {
            }
            this.nickname = "";
        }
    }

    public void exitFromChat() {
        this.isConnect.set(false);
        this.closeConnection();
    }

    public void closeClient() {
        this.exitFromChat();
        System.exit(0);
    }

    public void connectToServer(Login login) throws IOException {
        this.socket = new Socket(login.getServerAddress(), login.getPortNumber());
        this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.writer = new PrintWriter(this.socket.getOutputStream());
        this.listenerTreadInterrupte = false;
        this.sendToServer(login);
        this.listenToServer();
    }

    public void sendToServer(JSONObject jsonObject) {
        if (this.writer != null) {
            this.writer.println(jsonObject.serialize());
            this.writer.flush();
        }
    }

    public JSONObject getJSONObjectFromServer() {
        while (this.reader != null) {
            try {
                if (this.reader.ready()) {
                    JSONDeserialization deserialization = new JSONDeserialization();
                    return deserialization.deserialize(this.reader.readLine());
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                break;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public void listenToServer() {
        this.listenerServerThread = new Thread(() -> {
            while (!this.listenerTreadInterrupte) {
                JSONObject jsonObject = this.getJSONObjectFromServer();
                if (jsonObject != null) {
                    this.jsonObjectSimpleObject.set(jsonObject);
                }
            }
        });
        listenerServerThread.start();
    }
}
