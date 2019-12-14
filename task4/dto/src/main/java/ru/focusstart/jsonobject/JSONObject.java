package ru.focusstart.jsonobject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface JSONObject {
    String getOwnName();

    default String serialize() {
        String serializeString;
        try {
            serializeString = new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            serializeString = "Сообщение не отправленно! Ошибка сериализации";
        }

        return serializeString;
    }
}