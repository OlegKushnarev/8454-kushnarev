package ru.focusstart.serialization;

import ru.focusstart.jsonobject.JSONObject;
import ru.focusstart.jsonobject.JSONObjectCreater;
import ru.focusstart.jsonobject.JSONObjects;

public class JSONDeserialization {
    public JSONObject deserialize(String serializedString) {
        JSONObject jsonObject = null;
        for (JSONObjects object :
                JSONObjects.values()) {
            System.out.println(object);
            JSONObjectCreater creater = object.getJSONObjectCreater();
            if (creater != null) {
                jsonObject = creater.getObjectFromJSON(serializedString);
            }
            if (jsonObject != null) {
                System.out.println("Объект получен");
                //System.out.println(jsonObject);
                return jsonObject;
            }
        }
        return null;
    }
}