package ru.focusstart.model.booleanproperties;

import javafx.beans.property.SimpleBooleanProperty;
import ru.focusstart.controller.Facade3;
import ru.focusstart.controller.Facade4;

public interface BooleanPropertiesCreater {
    SimpleBooleanProperty getBooleanProperty();
}

class EnterBooleanPropertiesCreater implements BooleanPropertiesCreater {

    @Override
    public SimpleBooleanProperty getBooleanProperty() {
        SimpleBooleanProperty simpleBooleanProperty = new SimpleBooleanProperty(false);
        simpleBooleanProperty.addListener(new Facade3());
        return simpleBooleanProperty;
    }
}

class IsConnectBooleanPropertiesCreater implements BooleanPropertiesCreater {

    @Override
    public SimpleBooleanProperty getBooleanProperty() {
        SimpleBooleanProperty simpleBooleanProperty = new SimpleBooleanProperty(false);
        simpleBooleanProperty.addListener(new Facade4());
        return simpleBooleanProperty;
    }
}