package ru.focusstart.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.focusstart.encryption.Encryption;
import ru.focusstart.jsonobject.JSONObject;
import ru.focusstart.reader.PropertieReader;

import java.io.IOException;

public class Login implements Encryption, JSONObject {
    private String serverAddress;
    private String userNickname;
    private int portNumber;

    public Login() {
        super();
    }

    public Login(String serverAddress, int portNumber, String userNickname) {
        this.serverAddress = serverAddress;
        this.portNumber = portNumber;
        this.userNickname = userNickname;
    }
/*
    public Login(String fullFileName) throws IOException {


        this(loginReader.read("server.address"),
                Integer.parseInt(loginReader.read("server.port")),
                loginReader.read("nickname"));
    }*/

    public String getServerAddress() {
        return serverAddress;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public int getPortNumber() {
        return portNumber;
    }

    @Override
    public String serialize() {
        String serializedLogin;
        try {
            serializedLogin =  new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            serializedLogin = "Сообщение не отправленно! Ошибка сериализации";
        }

        return serializedLogin;
    }

    @Override
    public String getOwnName() {
        return "LOGIN";
    }
/*
    @Override
    public Encryption deserialize(String serializedObject) {
        Login deserializedLogin;
        try {
            System.out.println("До десериализации " + serializedObject);
            deserializedLogin =  new ObjectMapper().readValue(serializedObject, Login.class);
            System.out.println("После десериализации");
        } catch (JsonProcessingException e) {
            System.out.println("Ошибка в сереализации " + e.getMessage());
            deserializedLogin = null;
        }
        return deserializedLogin;
    }*/
}
