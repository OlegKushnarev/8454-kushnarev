package ru.focusstart.production;

public enum Events {
    MADE("Ресурс произведён."),
    PUT("Ресурс помещён на склад."),
    TAKEN("Ресурс забран со склада."),
    CONSUMED("Ресурс потреблён."),
    NONE(null);

    private String event;

    public String getEvent() {
        return this.event;
    }

    Events(String event) {
        this.event = event;
    }
}
