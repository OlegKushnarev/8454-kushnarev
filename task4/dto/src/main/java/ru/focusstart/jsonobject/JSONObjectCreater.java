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
        Message message;
        try {
            message = this.getMessage(serializedString);
        } catch (JsonProcessingException e) {
            message = null;
        }
        return message;
    }
}

class LoginJSONObjectCreater implements JSONObjectCreater {

    private Login getLogin(String serializedString) throws JsonProcessingException {
        return new ObjectMapper().readValue(serializedString, Login.class);
    }

    @Override
    public JSONObject getObjectFromJSON(String serializedString) {
        Login login;
        try {
            login = this.getLogin(serializedString);
            System.out.println(login);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            login = null;
        }
        return login;
    }
}

class ContactListJSONObjectCreater implements JSONObjectCreater {
    private ContactList getContactList(String serializedString) throws JsonProcessingException {
        return new ObjectMapper().readValue(serializedString, ContactList.class);
    }

    @Override
    public JSONObject getObjectFromJSON(String serializedString) {
        ContactList contactList;
        try {
            contactList = this.getContactList(serializedString);
        } catch (JsonProcessingException e) {
            contactList = null;
        }
        return contactList;
    }
}