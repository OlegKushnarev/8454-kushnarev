package ru.focusstart.contactlist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.focusstart.encryption.Encryption;

import java.util.ArrayList;
import java.util.List;

public class ContactList extends ArrayList<String> implements Encryption {
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
}
