package ru.focusstart.model;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;

import java.util.ArrayList;
import java.util.List;

public class Enter extends SimpleBooleanProperty/* implements ObservableBooleanValue*/{
    boolean enter;
    //List<InvalidationListener> listenerList = new ArrayList<>();
   //ObservableBooleanValue ffff = new SimpleBooleanProperty(false);

    public Enter(boolean enter) {
        this.enter = enter;

    }
/*
    @Override
    public boolean get() {
        return enter;
    }

    @Override
    public void addListener(ChangeListener<? super Boolean> changeListener) {
        this.listenerList.add((InvalidationListener) changeListener);
    }

    @Override
    public void removeListener(ChangeListener<? super Boolean> changeListener) {
        this.listenerList.remove(changeListener);
    }

    @Override
    public Boolean getValue() {
        return enter;
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        this.listenerList.add(invalidationListener);
    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        this.listenerList.remove(invalidationListener);
    }*/
}
