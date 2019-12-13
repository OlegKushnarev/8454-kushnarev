package ru.focusstart.view.button;

public enum Buttons {
    ENTER(new EnterButtonCreater()),
    SEND(new SendButtonCreater()),
    NONE(null);

    private ButtonCreater buttonCreater;

    public ButtonCreater getButtonCreater() {
        return buttonCreater;
    }

    Buttons(ButtonCreater buttonCreater) {
        this.buttonCreater = buttonCreater;
    }
}
