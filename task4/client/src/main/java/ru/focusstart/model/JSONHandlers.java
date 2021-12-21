package ru.focusstart.model;

public enum JSONHandlers {
    MESSAGE(new MessageHandler()),
    CONTACT_LIST(new ContactListMessageHandler()),
    NICKNAME_ACCEPTED_SERVICE_MESSAGE(new NicknameAcceptedServiceMessageHandler()),
    NICKNAME_REJECTED_SERVICE_MESSAGE(new NicknameRejectedServiceMessageHandler()),
    MESSAGE_DELIVERED_SERVICE_MESSAGE(new MessageDeliveredServiceMessageHandler()),
    SERVER_STOP_SERVICE_MESSAGE(new ServerStopServiceMessageHandler()),
    NONE(null);

    private JSONHandler jsonHandler;

    public JSONHandler getJsonHandler() {
        return jsonHandler;
    }

    JSONHandlers(JSONHandler jsonHandler) {
        this.jsonHandler = jsonHandler;
    }
}
