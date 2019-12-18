package ru.focusstart.jsonobject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.focusstart.contactlist.ContactList;
import ru.focusstart.login.Login;
import ru.focusstart.message.Message;

public interface JSONObjectCreater {
    JSONObject getObjectFromJSON(String serializedString);
}

class MessageJSONObjectCreater implements JSONObjectCreater {

    private Message getMessage(String serializedString) throws JsonProcessingException {
        return new ObjectMapper().readValue(serializedString, Message.class);
    }

    @Override
    public JSONObject getObjectFromJSON(String serializedString) {
        try {
            return this.getMessage(serializedString);
        } catch (JsonProcessingException ignored) {
        }
        return null;
    }
}

class LoginJSONObjectCreater implements JSONObjectCreater {

    private Login getLogin(String serializedString) throws JsonProcessingException {
        return new ObjectMapper().readValue(serializedString, Login.class);
    }

    @Override
    public JSONObject getObjectFromJSON(String serializedString) {
        try {
            return this.getLogin(serializedString);
        } catch (JsonProcessingException ignored) {
        }
        return null;
    }
}

class ContactListJSONObjectCreater implements JSONObjectCreater {
    private ContactList getContactList(String serializedString) throws JsonProcessingException {
        return new ObjectMapper().readValue(serializedString, ContactList.class);
    }

    @Override
    public JSONObject getObjectFromJSON(String serializedString) {
        try {
            return this.getContactList(serializedString);
        } catch (JsonProcessingException ignored) {
        }
        return null;
    }
}