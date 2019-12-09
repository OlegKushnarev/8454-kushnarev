package ru.focusstart.serialization;

import ru.focusstart.jsonobject.JSONObject;
import ru.focusstart.jsonobject.JSONObjects;

public class JSONDeserialization {
    public JSONObject deserialize(String serializedString) {
        JSONObject jsonObject;
        for (JSONObjects object :
                JSONObjects.values()) {
            jsonObject = object.getJSONObjectCreater().getObjectFromJSON(serializedString);
            if (jsonObject != null) {
                return jsonObject;
            }
        }
        return null;
    }
}
/*
    public String serializeObject(Message message) {
        String serializedMessage;
        try {
            serializedMessage =  new ObjectMapper().writeValueAsString(message);
        } catch (JsonProcessingException e) {
            serializedMessage = "Сообщение не отправленно! Ошибка сериализации";
        }

        return serializedMessage;
    }

    public String serializeObject(Login login) {
        String serializedLogin;
        try {
            serializedLogin =  new ObjectMapper().writeValueAsString(login);
        } catch (JsonProcessingException e) {
            serializedLogin = "Сообщение не отправленно! Ошибка сериализации";
        }

        return serializedLogin;
    }

    public Message deserializeMessage(String serializedMessage) {
        Message deserializedMessage;
        try {
            System.out.println("До десериализации " + serializedMessage);
            deserializedMessage =  new ObjectMapper().readValue(serializedMessage, Message.class);
            System.out.println("После десериализации");
        } catch (JsonProcessingException e) {
            System.out.println("Ошибка в сереализации " + e.getMessage());
            deserializedMessage = null;
        }
        return deserializedMessage;
    }

    public Login deserializeLogin(String serializedLogin) {
        Login deserializedLogin;
        try {
            System.out.println("До десериализации " + serializedLogin);
            deserializedLogin =  new ObjectMapper().readValue(serializedLogin, Login.class);
            System.out.println("После десериализации");
        } catch (JsonProcessingException e) {
            System.out.println("Ошибка в сереализации " + e.getMessage());
            deserializedLogin = null;
        }
        return deserializedLogin;
    }

    public ContactList deserializeContactList(String serializedContactList) {
        ContactList deserializedContactList;
        try {
            System.out.println("До десериализации " + serializedContactList);
            deserializedContactList =  new ObjectMapper().readValue(serializedContactList, ContactList.class);
            System.out.println("После десериализации");
        } catch (JsonProcessingException e) {
            System.out.println("Ошибка в сереализации " + e.getMessage());
            deserializedContactList = null;
        }
        return deserializedContactList;
    }
}*/
