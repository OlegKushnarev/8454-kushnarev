package ru.focusstart.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.focusstart.jsonobject.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements JSONObject {
    private String text;
    private Date date;
    private String ownName;

    public Message() {
        super();
        this.date = new Date();
    }

    public Message(String text) {
        this.text = text;
        this.date = new Date();
        this.ownName = "MESSAGE";
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

    public void setDate(Date date) {
        this.date = date;
    }
/*
    public boolean isEmpty() {
        return this.message.isEmpty();
    }*/

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        StringBuilder stringMessage = new StringBuilder();
        stringMessage.append(dateFormat.format(this.date)).append(": ").append(this.text);
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

    @Override
    public String getOwnName() {
        return this.ownName;
    }

    protected void setOwnName(String ownName) {
        this.ownName = ownName;
    }
}
