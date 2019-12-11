package ru.focusstart.model;

public enum JSONViewers {
    MESSAGE(new MessageViewer()),
    SERVICE_MESSAGE(new ServiceMessageViewer()),
    CONTACT_LIST(new ContactListMessageViewer()),
    LOGIN(null),
    NONE(null);

    private JSONViewer jsonViewer;

    public JSONViewer getJsonViewer() {
        return jsonViewer;
    }

    JSONViewers(JSONViewer jsonViewer) {
        this.jsonViewer = jsonViewer;
    }
}
