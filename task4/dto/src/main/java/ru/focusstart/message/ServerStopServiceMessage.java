package ru.focusstart.message;

public class ServerStopServiceMessage extends ServiceMessage {
    public ServerStopServiceMessage() {
        super();
        this.setOwnName("SERVER_STOP_SERVICE_MESSAGE");
    }
}