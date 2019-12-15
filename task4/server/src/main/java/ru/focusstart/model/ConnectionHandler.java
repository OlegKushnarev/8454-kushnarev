package ru.focusstart.model;

import ru.focusstart.connection.ConnectionParameter;
import ru.focusstart.jsonobject.JSONObject;
import ru.focusstart.login.Login;
import ru.focusstart.message.Message;
import ru.focusstart.message.MessageDeliveredServiceMessage;
import ru.focusstart.message.NicknameAcceptedServiceMessage;
import ru.focusstart.message.NicknameRejectedServiceMessage;

import java.io.IOException;

public interface ConnectionHandler {
    void handle(ConnectionParameter connectionParameter) throws IOException;
}

class ConnectionRequestHandler implements ConnectionHandler {

    @Override
    public void handle(ConnectionParameter connectionParameter) throws IOException {
        JSONObject jsonObject = connectionParameter.getJsonObject();
        if (jsonObject instanceof Login) {
            Login login = (Login) jsonObject;
            ServerModel chatServer = ServerModel.getInstance();
            String userNickName = login.getUserNickname();
            if (chatServer.checkNickname(userNickName)) {
                chatServer.sendMessage(connectionParameter.getWriter(), new NicknameRejectedServiceMessage());
            } else {
                connectionParameter.setNickname(userNickName);
                connectionParameter.setJsonObject(null);
                chatServer.addConnection(connectionParameter);
                chatServer.sendMessage(connectionParameter.getWriter(), new NicknameAcceptedServiceMessage());
                chatServer.sendMessageToEveryone(new Message("server", userNickName + " присоединился к чату"));
                chatServer.sendMessageToEveryone(chatServer.getContactList());
            }
        }
    }
}

class MessageExchangeHandler implements ConnectionHandler {

    @Override
    public void handle(ConnectionParameter connectionParameter) throws IOException {
        JSONObject jsonObject = connectionParameter.getJsonObject();
        if (jsonObject instanceof Message) {
            Message message = (Message) jsonObject;
            if (!message.getText().isEmpty()) {
                ServerModel chatServer = ServerModel.getInstance();
                chatServer.sendMessageToEveryone(message);
                chatServer.sendMessage(connectionParameter.getWriter(), new MessageDeliveredServiceMessage());
            }
            connectionParameter.setJsonObject(null);
        }
    }
}

class LogoutHandler implements ConnectionHandler {

    @Override
    public void handle(ConnectionParameter connectionParameter) throws IOException {
        JSONObject jsonObject = connectionParameter.getJsonObject();
        if (jsonObject instanceof Message) {
            ServerModel chatServer = ServerModel.getInstance();
            String userNickname = connectionParameter.getNickname();
            chatServer.removeConnection(connectionParameter);
            chatServer.sendMessageToEveryone(new Message("server: ", userNickname + " покинул чат"));
            chatServer.sendMessageToEveryone(chatServer.getContactList());
        }
    }
}