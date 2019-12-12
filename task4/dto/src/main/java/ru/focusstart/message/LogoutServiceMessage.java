package ru.focusstart.message;

public class LogoutServiceMessage extends ServiceMessage {
    public LogoutServiceMessage() {
        super();
        this.setOwnName("LOGOUT_SERVICE_MESSAGE");
    }
/*
    @Override
    public String getOwnName() {
        return "LOGOUT_SERVICE_MESSAGE";
    }*/
}
