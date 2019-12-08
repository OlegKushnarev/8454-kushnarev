package ru.focusstart.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import ru.focusstart.contactlist.ContactList;
import ru.focusstart.controller.Facade3;
import ru.focusstart.encryption.Encryption;
import ru.focusstart.login.Login;
import ru.focusstart.message.Message;
import ru.focusstart.serialization.JSONDeserialization;

public class ChatModel/* extends Observable */{
    private static ChatModel instance;
    SimpleBooleanProperty OnEnter;
    boolean isConnect;
    private ContactList nicknames;
    private Login login;
    private Message messageFromServer;
    private Message messageFromUser;
    List<String> nickNames;
    BufferedReader reader;
    PrintWriter writer;

    public static ChatModel getInstance() {
        if (instance == null) {
            instance = new ChatModel();
        }
        return instance;
    }

    private ChatModel() {
        super();
        this.OnEnter = new SimpleBooleanProperty(false);
        this.isConnect = false;
        //this.serverAddress = "";
        //this.userNickname = "";
        //nicknames = new ContactList();
        //this.messageFromServer = "";
        //this.messageFromUser = null;
        this.nickNames = new ArrayList<>();
    }

    public boolean isConnect() {
        return isConnect;
    }

   /* public String getUserNickname() {
        return userNickname;
    }

    public String getServerAddress() {
        return serverAddress;
    }*/

    public List<String> getNickNames() {
        return nickNames;
    }

   /* public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }*/

    public Login getLogin() {
        return login;
    }

    public Message getMessageFromServer() {
        return messageFromServer;
    }

    public Message getMessageFromUser() {
        return messageFromUser;
    }

    /*public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }*/

    public void setLogin(Login login) {
        this.login = login;
    }

    public void setMessageFromUser(Message messageFromUser) {
        this.messageFromUser = messageFromUser;
    }

    public void enterToChat() {
        OnEnter.addListener(new Facade3());
        OnEnter.set(true);
       // setChanged();
       // notifyObservers();
    }

    public void connectToServer() throws IOException {
        if (login.getServerAddress().isEmpty()) {
            throw new IllegalArgumentException("Не задан адрес сервера!");
        }

        if (login.getPortNumber() == 0) {
            throw new IllegalArgumentException("Не задан порт подключения!");
        }

        if (login.getUserNickname().isEmpty()) {
            throw new IllegalArgumentException("Не задан ник!");
        }

        Socket socket = new Socket(login.getServerAddress(), login.getPortNumber());
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream());
      /*  JSONSerialization jsonSerialization = new JSONSerialization();
        writer.println(jsonSerialization.serializeObject(this.login));
        writer.flush();*/
        this.sendToServer(this.login);
        String message = "";
        while (message.isEmpty()) {
            if (reader.ready()) {
                message = reader.readLine();
                //break;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        JSONDeserialization deserializationContactList = new JSONDeserialization();
        nickNames = deserializationContactList.deserializeContactList(message);
        if (nickNames == null) {
            this.messageFromServer = deserializationContactList.deserializeMessage(message);
        }

        this.isConnect = true;
        //setChanged();
       // notifyObservers();
    }

    public void sendToServer(Encryption encryptionObject) {
        //System.out.println("ChatModel " + this.messageFromUser);
        //JSONSerialization jsonSerialization = new JSONSerialization();
        //String str = jsonSerialization.serializeObject(this.messageFromUser);
        //System.out.println(str);
        //writer.println(jsonSerialization.serializeObject(object));
        writer.println(encryptionObject.serialize());
        writer.flush();
    }

    public void listenToUser() {
        Thread ListenerThread = new Thread(() -> {
            boolean interrupted = false;
            while (!interrupted) {
                if (this.messageFromUser != null) {
                    this.sendToServer(this.messageFromUser);
                    this.messageFromUser = null;
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    interrupted = true;
                }
            }
        });
        ListenerThread.start();
    }

    public void listenToServer() {
        while (true) {
            try {
                String message = reader.readLine();

                // System.out.println("Получили сообщение от сервера");
               // setChanged();
              //  notifyObservers();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
