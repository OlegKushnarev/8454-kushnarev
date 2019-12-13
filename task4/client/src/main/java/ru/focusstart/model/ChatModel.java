package ru.focusstart.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import ru.focusstart.controller.Facade5;
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
    private String nickname;
    private SimpleObjectProperty<JSONObject> jsonObjectSimpleObject;
    BufferedReader reader;
    PrintWriter writer;

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
        this.jsonObjectSimpleObject.addListener(new Facade5());
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
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void closeConnection() {
        try {
            if (socket != null) {
                socket.close();
                System.out.println("Соединение разорвано.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void waitExit() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::closeConnection));
    }

    public void exitFromChat() {
        this.closeConnection();
        this.isConnect.set(false);
    }

    public void closeClient() {
        this.exitFromChat();
        System.exit(0);
    }

    public void connectToServer(Login login) throws IOException {
        Socket socket = new Socket(login.getServerAddress(), login.getPortNumber());
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream());
        this.sendToServer(login);
        this.listenToServer();
    }

    public void sendToServer(JSONObject jsonObject) {
        writer.println(jsonObject.serialize());
        writer.flush();
    }

    public JSONObject getJSONObjectFromServer() {
        String message = "";
        while (message.isEmpty()) {
            try {
                if (reader.ready()) {
                    message = reader.readLine();
                    //break;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        JSONDeserialization deserialization = new JSONDeserialization();
        return deserialization.deserialize(message);
    }

    public void listenToServer() {
        Thread ListenerServerThread = new Thread(() -> {
            //boolean interrupted = false;
            while (true) {
                JSONObject jsonObject = this.getJSONObjectFromServer();
                if (jsonObject == null) {
                    System.out.println("Сервер прислал лажу");
                } else {
                    System.out.println("Ответ получен");
                    this.jsonObjectSimpleObject.set(jsonObject);
                }
            }
        }, "Наблюдатель за сервером");
        ListenerServerThread.start();
    }
}
