package ru.focusstart.view;

import ru.focusstart.view.ChatAreaCreater;
import ru.focusstart.view.ContactAreaCreater;
import ru.focusstart.view.MessageAreaCreater;

public enum TextAreas {
    CHATAREA(new ChatAreaCreater()),
    CONTACTAREA(new ContactAreaCreater()),
    MESSAGEAREA(new MessageAreaCreater()),
    NONE(null);

    private TextAreaCreater textAreaCreater;

    public TextAreaCreater getTextAreaCreater() {
        return textAreaCreater;
    }

    TextAreas(TextAreaCreater textAreaCreater) {
        this.textAreaCreater = textAreaCreater;
    }
}