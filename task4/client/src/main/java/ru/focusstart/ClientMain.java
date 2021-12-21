package ru.focusstart;

import ru.focusstart.model.ChatModel;

public class ClientMain {
    public static void main(String[] args) {
        ChatModel chatClient = ChatModel.getInstance();
        chatClient.login();
    }
}