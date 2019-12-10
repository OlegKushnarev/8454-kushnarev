package ru.focusstart.message;

public enum ServiceMessages {
    NICKNAME_ACCEPTED(""),
    NICKNAME_REJECTED("Введённый логин отклонён сервером."),
    NONE("");

    private String serviceMessageText;

    public String getServiceMessageText() {
        return serviceMessageText;
    }

    ServiceMessages(String serviceMessageText) {
        this.serviceMessageText = serviceMessageText;
    }
}
