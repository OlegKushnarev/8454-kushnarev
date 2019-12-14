package ru.focusstart.login;

import ru.focusstart.jsonobject.JSONObject;

public class Login implements JSONObject {
    private String serverAddress;
    private String userNickname;
    private int portNumber;
    private final String ownName = "LOGIN";

    public Login() {
        super();
    }

    public Login(String serverAddress, int portNumber, String userNickname) {
        this.serverAddress = serverAddress;
        this.portNumber = portNumber;
        this.userNickname = userNickname;
    }

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
    public String getOwnName() {
        return this.ownName;
    }

}
