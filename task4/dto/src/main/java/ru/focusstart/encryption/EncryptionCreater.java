package ru.focusstart.encryption;

import ru.focusstart.contactlist.ContactList;
import ru.focusstart.login.Login;
import ru.focusstart.message.Message;

public interface EncryptionCreater {
    Encryption getEncription();
}

class MessageEncryptionCreater implements EncryptionCreater {

    @Override
    public Encryption getEncription() {
        return new Message();
    }
}

class LoginEncryptionCreater implements EncryptionCreater {

    @Override
    public Encryption getEncription() {
        return new Login();
    }
}

class ContactListEncryptionCreater implements EncryptionCreater {

    @Override
    public Encryption getEncription() {
        return new ContactList();
    }
}