package ru.focusstart.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.focusstart.jsonobject.JSONObject;
import ru.focusstart.model.JSONViewer;
import ru.focusstart.model.JSONViewers;

public class Facade5 implements ChangeListener<JSONObject> {

    @Override
    public void changed(ObservableValue<? extends JSONObject> observableValue, JSONObject jsonObject, JSONObject t1) {
        JSONViewer jsonViewer = JSONViewers.valueOf(t1.getOwnName()).getJsonViewer();
        jsonViewer.view(t1);
    }
}