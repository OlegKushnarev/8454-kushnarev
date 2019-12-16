package ru.focusstart.message;

public class MessageDeliveredServiceMessage extends ServiceMessage {
    public MessageDeliveredServiceMessage() {
        super("Message delivered");
        this.setOwnName("MESSAGE_DELIVERED_SERVICE_MESSAGE");
    }
}