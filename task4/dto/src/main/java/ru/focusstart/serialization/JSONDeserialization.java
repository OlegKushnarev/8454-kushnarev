package ru.focusstart.serialization;

import ru.focusstart.jsonobject.JSONObject;
import ru.focusstart.jsonobject.JSONObjectCreater;
import ru.focusstart.jsonobject.JSONObjects;

public class JSONDeserialization {
    public JSONObject deserialize(String serializedString) {
        JSONObject jsonObject = null;
        for (JSONObjects object :
                JSONObjects.values()) {
            JSONObjectCreater creater = object.getJSONObjectCreater();
            if (creater != null) {
                jsonObject = creater.getObjectFromJSON(serializedString);
            }
            if (jsonObject != null) {
                return jsonObject;
            }
        }
        return null;
    }
}