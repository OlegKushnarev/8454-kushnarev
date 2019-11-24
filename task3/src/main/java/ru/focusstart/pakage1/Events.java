package ru.focusstart.pakage1;

public enum Events {
    MADE("Ресурс произведён.\n"),
    PUT("Ресурс помещён на склад.\n"),
    TAKEN("Ресурс забран со склада.\n"),
    CONSUMED("Ресурс потреблён.\n"),
    NONE(null);

    private String event;

    public String getEvent() {
        return this.event;
    }

    Events(String event) {
        this.event = event;
    }
}
