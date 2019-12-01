package ru.focusstart.model;

import ru.focusstart.model.ConnectWindowCreater;
import ru.focusstart.model.MainWindowCreater;
import ru.focusstart.model.WindowCreater;

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
