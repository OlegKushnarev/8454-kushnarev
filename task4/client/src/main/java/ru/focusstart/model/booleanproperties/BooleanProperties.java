package ru.focusstart.model.booleanproperties;

public enum BooleanProperties {
    ON_ENTER(new EnterBooleanPropertiesCreater()),
    IS_CONNECT(new IsConnectBooleanPropertiesCreater()),
    HAVE_MESSAGE(new HaveMessageBooleanPropertiesCreater()),
    NONE(null);

    private BooleanPropertiesCreater booleanPropertiesCreater;

    BooleanProperties(BooleanPropertiesCreater booleanPropertiesCreater) {
        this.booleanPropertiesCreater = booleanPropertiesCreater;
    }

    public BooleanPropertiesCreater getBooleanPropertiesCreater() {
        return booleanPropertiesCreater;
    }
}