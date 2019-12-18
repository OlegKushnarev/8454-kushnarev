package ru.focusstart.model;

public enum ConnectionHandlers {
    LOGIN(new ConnectionRequestHandler()),
    MESSAGE(new MessageExchangeHandler()),
    LOGOUT_SERVICE_MESSAGE(new LogoutHandler()),
    NONE(null);

    private ConnectionHandler ConnectionHandler;

    public ConnectionHandler getConnectionHandler() {
        return ConnectionHandler;
    }

    ConnectionHandlers(ConnectionHandler ConnectionHandler) {
        this.ConnectionHandler = ConnectionHandler;
    }
}