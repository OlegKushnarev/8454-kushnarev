package ru.focusstart.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.focusstart.message.LogoutServiceMessage;
import ru.focusstart.model.ChatModel;

public class LogoutListener implements ChangeListener<Boolean> {

    @Override
    public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
        if (!t1) {
            ChatModel chatClient = ChatModel.getInstance();
            chatClient.sendToServer(new LogoutServiceMessage());
        }
    }
}