package ru.focusstart.view;

public enum Windows {
    MAIN(new MainWindowCreater()),
    CONNECT(new ConnectWindowCreater()),
    NONE(null);

    private WindowCreater windowCreater;

    public WindowCreater getWindowCreater() {
        return windowCreater;
    }

    Windows(WindowCreater windowCreater) {
        this.windowCreater = windowCreater;
    }
}