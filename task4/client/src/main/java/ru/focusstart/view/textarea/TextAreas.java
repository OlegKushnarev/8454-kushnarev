package ru.focusstart.view.textarea;

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