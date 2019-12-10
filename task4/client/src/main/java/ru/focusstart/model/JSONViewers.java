package ru.focusstart.model;

public enum JSONViewers {
    MESSAGE_VIEWER(new MessageViewer()),
    SERVICE_MESSAGE_VIEWER(new ServiceMessageViewer()),
    CONTACT_LIST_VIEWER(new ContactListMessageViewer()),
    NONE(null);

    private JSONViewer jsonViewer;

    public JSONViewer getJsonViewer() {
        return jsonViewer;
    }

    JSONViewers(JSONViewer jsonViewer) {
        this.jsonViewer = jsonViewer;
    }
}
