package ru.focusstart.model;

import ru.focusstart.connection.ConnectionParameter;
import ru.focusstart.jsonobject.JSONObject;
import ru.focusstart.login.Login;
import ru.focusstart.message.Message;

public interface ConnectionHandler {
    void handle(ConnectionParameter connectionParameter);
}

class MessageExchangeHandler implements ConnectionHandler {

    @Override
    public void handle(ConnectionParameter connectionParameter) {
        JSONObject jsonObject = connectionParameter.getJsonObject();
        if (jsonObject instanceof Message) {
            Message message = (Message) jsonObject;
            if (!message.getText().isEmpty()) {
                ServerModel chatServer = ServerModel.getInstance();
                chatServer.sendMessageToEveryone(message);
                //TODO Сформировать сервисное сообщение что логин принят
                chatServer.sendMessage(connectionParameter.getWriter(), );
                //TODO Сформировать сообщение что сообщение отправлено
                chatServer.sendMessage(connectionParameter.getWriter(), );
            }

            connectionParameter.setJsonObject(null);
        }
    }
}

class ConnectionRequestHandler implements ConnectionHandler {

    @Override
    public void handle(ConnectionParameter connectionParameter) {
        JSONObject jsonObject = connectionParameter.getJsonObject();
        if (jsonObject instanceof Login) {
            Login login = (Login) jsonObject;
            ServerModel chatServer = ServerModel.getInstance();
            if (chatServer.checkNickname(login.getUserNickname())) {
                //TODO Сформировать сервисное сообщение что логин не принят
                chatServer.sendMessage(connectionParameter.getWriter(), );
            } else {
                chatServer.addConnection(connectionParameter);
                /*
                readers.add(reader);
                writers.add(writer);
                clients.add(clientSocket);*/
                //TODO Сформировать сервисное сообщение что логин принят
                chatServer.sendMessage(connectionParameter.getWriter(), );
                //TODO Сформировать сообщение что контакт вошёл в чат
                chatServer.sendMessageToEveryone();
                //TODO Отправить всем список контактов
                chatServer.sendMessageToEveryone();
            }
            connectionParameter.setJsonObject(null);
        }
    }
}