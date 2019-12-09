package ru.focusstart.jsonobject;

public enum JSONObjects {
    MESSAGE(new MessageJSONObjectCreater()),
    LOGIN(new LoginJSONObjectCreater()),
    CONTACTLIST(new ContactListJSONObjectCreater()),
    NONE(null);

    private JSONObjectCreater JSONObjectCreater;

    public JSONObjectCreater getJSONObjectCreater() {
        return JSONObjectCreater;
    }

    JSONObjects(JSONObjectCreater JSONObjectCreater) {
        this.JSONObjectCreater = JSONObjectCreater;
    }
}
