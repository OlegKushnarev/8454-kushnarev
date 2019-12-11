package ru.focusstart.contactlist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleListProperty;
//import ru.focusstart.controller.ContactListner;
import ru.focusstart.encryption.Encryption;
import ru.focusstart.jsonobject.JSONObject;

import java.util.ArrayList;

public class ContactList extends ArrayList<String> implements Encryption, JSONObject {
 /*   private List<String> nickNames;

    public ContactList() {
        //super();
        this.nickNames = new ArrayList<>();
    }

    public ContactList(List<String> nickNames) {
        this.nickNames = nickNames;
    }



    public boolean contains(String nickname) {
        return nickNames.contains(nickname);
    }*/

    public ContactList() {
        super();
       // this.addListener(new ContactListner());
    }

    @Override
    public String serialize() {
        String serializedLogin;
        try {
            serializedLogin =  new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            serializedLogin = "Сообщение не отправленно! Ошибка сериализации";
        }

        return serializedLogin;
    }

    @Override
    public String getOwnName() {
        return "CONTACT_LIST";
    }
}
