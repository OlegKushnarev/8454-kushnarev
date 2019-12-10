package ru.focusstart.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.focusstart.jsonobject.JSONObject;
import ru.focusstart.message.ServiceMessage;

import javax.swing.*;

public class Facade6 implements ChangeListener<JSONObject> {
    @Override
    public void changed(ObservableValue<? extends JSONObject> observableValue, JSONObject jsonObject, JSONObject t1) {
        if (jsonObject instanceof ServiceMessage) {
            ServiceMessage serviceMessage = (ServiceMessage) jsonObject;

        }
    }
}
