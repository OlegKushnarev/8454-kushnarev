package ru.focusstart.model;

public enum ConnectionHandlers {
    MESSAGE(new MessageExchangeHandler()),
    //SERVICE_MESSAGE(new ServiceMessageHandler()),
    //CONTACT_LIST(new ContactListHandler()),
    LOGIN(new ConnectionRequestHandler()),
    NONE(null);

    private ConnectionHandler ConnectionHandler;

    public ConnectionHandler getConnectionHandler() {
        return ConnectionHandler;
    }

    ConnectionHandlers(ConnectionHandler ConnectionHandler) {
        this.ConnectionHandler = ConnectionHandler;
    }
}