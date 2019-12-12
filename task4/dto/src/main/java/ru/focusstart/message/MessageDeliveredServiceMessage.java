package ru.focusstart.message;

public class MessageDeliveredServiceMessage extends ServiceMessage {
    public MessageDeliveredServiceMessage() {
        super();
        this.setOwnName("MESSAGE_DELIVERED_SERVICE_MESSAGE");
    }
/*
    @Override
    public String getOwnName() {
        return "MESSAGE_DELIVERED_SERVICE_MESSAGE";
    }*/
}
