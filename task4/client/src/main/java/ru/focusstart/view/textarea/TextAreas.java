package ru.focusstart.view.textarea;

import ru.focusstart.view.textarea.ChatAreaCreater;
import ru.focusstart.view.textarea.ContactAreaCreater;
import ru.focusstart.view.textarea.MessageAreaCreater;
import ru.focusstart.view.textarea.TextAreaCreater;

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