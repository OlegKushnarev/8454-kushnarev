package ru.focusstart.model.booleanproperties;

import javafx.beans.property.SimpleBooleanProperty;
import ru.focusstart.controller.ConnectionWindowListner;
import ru.focusstart.controller.MainWindowListner;
import ru.focusstart.controller.LogoutListner;

public interface BooleanPropertiesCreater {
    SimpleBooleanProperty getBooleanProperty();
}

class EnterBooleanPropertiesCreater implements BooleanPropertiesCreater {

    @Override
    public SimpleBooleanProperty getBooleanProperty() {
        SimpleBooleanProperty simpleBooleanProperty = new SimpleBooleanProperty(false);
        simpleBooleanProperty.addListener(new ConnectionWindowListner());
        return simpleBooleanProperty;
    }
}

class IsConnectBooleanPropertiesCreater implements BooleanPropertiesCreater {

    @Override
    public SimpleBooleanProperty getBooleanProperty() {
        SimpleBooleanProperty simpleBooleanProperty = new SimpleBooleanProperty(false);
        simpleBooleanProperty.addListener(new MainWindowListner());
        simpleBooleanProperty.addListener(new LogoutListner());
        return simpleBooleanProperty;
    }
}