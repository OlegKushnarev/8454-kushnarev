package ru.focusstart.encryption;

public enum Encryptions {
    MESSAGE(new MessageEncryptionCreater()),
    LOGIN(new LoginEncryptionCreater()),
    CONTACTLIST(new ContactListEncryptionCreater()),
    NONE(null);

    private EncryptionCreater encryptionCreater;

    public EncryptionCreater getEncryptionCreater() {
        return encryptionCreater;
    }

    Encryptions(EncryptionCreater encryptionCreater) {
        this.encryptionCreater = encryptionCreater;
    }
}
