package ru.focusstart.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.focusstart.jsonobject.JSONObject;
import ru.focusstart.model.JSONHandler;
import ru.focusstart.model.JSONHandlers;

public class Facade5 implements ChangeListener<JSONObject> {

    @Override
    public void changed(ObservableValue<? extends JSONObject> observableValue, JSONObject jsonObject, JSONObject t1) {
        if (t1 != null) {
            JSONHandler jsonHandler = JSONHandlers.valueOf(t1.getOwnName()).getJsonHandler();
            jsonHandler.view(t1);
        }
    }
}