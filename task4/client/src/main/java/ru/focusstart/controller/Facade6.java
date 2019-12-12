package ru.focusstart.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.focusstart.jsonobject.JSONObject;
import ru.focusstart.message.LogoutServiceMessage;
import ru.focusstart.message.ServiceMessage;
import ru.focusstart.model.ChatModel;

import javax.swing.*;

public class Facade6 implements ChangeListener<Boolean> {

    @Override
    public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
        if (!t1) {
            ChatModel chatClient = ChatModel.getInstance();
            chatClient.sendToServer(new LogoutServiceMessage());
        }
    }
}
