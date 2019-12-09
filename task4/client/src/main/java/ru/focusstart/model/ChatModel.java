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
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import ru.focusstart.contactlist.ContactList;
import ru.focusstart.controller.Facade3;
import ru.focusstart.encryption.Encryption;
import ru.focusstart.login.Login;
import ru.focusstart.message.Message;
import ru.focusstart.model.booleanproperties.BooleanProperties;
import ru.focusstart.serialization.JSONDeserialization;

import javax.swing.*;

public class ChatModel/* extends Observable */{
    private static ChatModel instance;
    private SimpleBooleanProperty OnEnter;
    private SimpleBooleanProperty isConnect;
    Encryption encryptionObject;
   // private Login login;
    private SimpleObjectProperty<Message> messageFromServer;
    private Message messageFromUser;
    //private SimpleListProperty<String> nickNames;
    private ContactList nicknames;
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
        this.OnEnter = BooleanProperties.ON_ENTER.getBooleanPropertiesCreater().getBooleanProperty();
        this.isConnect = BooleanProperties.IS_CONNECT.getBooleanPropertiesCreater().getBooleanProperty();
        //this.serverAddress = "";
        //this.userNickname = "";
        //nicknames = new ContactList();
        //this.messageFromServer = "";
        //this.messageFromUser = null;
        this.nicknames = new ContactList();

    }
/*
    public boolean isConnect() {
        return isConnect;
    }*/

   /* public String getUserNickname() {
        return userNickname;
    }

    public String getServerAddress() {
        return serverAddress;
    }*/
/*
    public List<String> getNickNames() {
        return nickNames;
    }
*/
   /* public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }*/
/*
    public Login getLogin() {
        return login;
    }
*/
    public Message getMessageFromServer() {
        return messageFromServer;
    }

    public Message getMessageFromUser() {
        return messageFromUser;
    }

    /*public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }*/
/*
    public void setLogin(Login login) {
        this.login = login;
    }
*/
    public void setMessageFromUser(Message messageFromUser) {
        this.messageFromUser = messageFromUser;
    }

    public boolean enterToChat() {
        this.OnEnter.set(true);

        while (!this.isConnect.getValue()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }

        return this.isConnect.get();
    }

    public void connectToServer(Login login) throws IOException {
        Socket socket = new Socket(login.getServerAddress(), login.getPortNumber());
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream());
      /*  JSONSerialization jsonSerialization = new JSONSerialization();
        writer.println(jsonSerialization.serializeObject(this.login));
        writer.flush();*/
        this.sendToServer(login);
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
        nicknames = deserializationContactList.deserializeContactList(message);
        if (nicknames == null) {
            this.messageFromServer = deserializationContactList.deserializeMessage(message);
        }

        this.isConnect.set(true);
        this.OnEnter.set(false);
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
