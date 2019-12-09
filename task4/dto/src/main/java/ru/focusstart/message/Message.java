package ru.focusstart.message;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.focusstart.encryption.Encryption;
import ru.focusstart.jsonobject.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

@JsonAutoDetect
public class Message implements Encryption, JSONObject {
    private String text;
    private Date date;

    public Message() {
        super();
    }

    public Message(String text) {
        this.text = text;
        this.date = new Date();
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
            serializedMessage =  new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            serializedMessage = "Сообщение не отправленно! Ошибка сериализации";
        }

        return serializedMessage;
    }

    @Override
    public void show() {

    }
/*
    @Override
    public Encryption deserialize(String serializedObject) {
        Message deserializedMessage;
        try {
            System.out.println("До десериализации " + serializedObject);
            deserializedMessage =  new ObjectMapper().readValue(serializedObject, Message.class);
            System.out.println("После десериализации");
        } catch (JsonProcessingException e) {
            System.out.println("Ошибка в сереализации " + e.getMessage());
            deserializedMessage = null;
        }
        return deserializedMessage;
    }*/
}
