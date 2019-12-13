package ru.focusstart.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.focusstart.jsonobject.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements JSONObject {
    private String text;
    private Date date;
    private String senderName;
    private String ownName;

    public Message() {
        super();
        this.date = new Date();
    }

    public Message(String text) {
        this();
        this.text = text;
        this.ownName = "MESSAGE";
    }

    public Message(String senderName, String text) {
        this(text);
        this.senderName = senderName;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public void setText(String text) {
        this.text = text;
    }
/*
    public void setDate(Date date) {
        this.date = date;
    }
*/
    @Override
    public String getOwnName() {
        return this.ownName;
    }

    protected void setOwnName(String ownName) {
        this.ownName = ownName;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    /*
    public boolean isEmpty() {
        return this.message.isEmpty();
    }*/

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        StringBuilder stringMessage = new StringBuilder();
        stringMessage.append(dateFormat.format(this.date))
                .append(" ")
                .append(this.senderName)
                .append(": ").
                append(this.text);
        return stringMessage.toString();
    }

    @Override
    public String serialize() {
        String serializedMessage;
        try {
            serializedMessage = new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            serializedMessage = "Сообщение не отправленно! Ошибка сериализации";
        }

        return serializedMessage;
    }
}
