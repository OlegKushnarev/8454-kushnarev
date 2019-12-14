package ru.focusstart.message;

public abstract class ServiceMessage extends Message {
    public ServiceMessage() {
        super();
    }

    public ServiceMessage(String messageText) {
        super("server", messageText);
    }
}