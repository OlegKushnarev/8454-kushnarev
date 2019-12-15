package ru.focusstart.contactlist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.focusstart.jsonobject.JSONObject;

import java.util.ArrayList;

public class ContactList extends ArrayList<String> implements JSONObject {
    private final String ownName = "CONTACT_LIST";

    public ContactList() {
        super();
    }

    @Override
    public String getOwnName() {
        return this.ownName;
    }
}
