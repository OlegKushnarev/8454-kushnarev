package ru.focusstart.message;

import ru.focusstart.model.ChatModel;

public class ServiceMessage extends Message {
    public ServiceMessage() {
        super();
    }

    @Override
    public String getJSONViewerName() {
        return "SERVICE_MESSAGE_VIEWER";
    }


}
