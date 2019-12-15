package ru.focusstart.message;

public class LogoutServiceMessage extends ServiceMessage {
    public LogoutServiceMessage() {
        super();
        this.setOwnName("LOGOUT_SERVICE_MESSAGE");
    }
}
