package ru.focusstart.message;

public class NicknameAcceptedServiceMessage extends ServiceMessage {

    public NicknameAcceptedServiceMessage() {
        super();
        this.setOwnName("NICKNAME_ACCEPTED_SERVICE_MESSAGE");
    }
/*
    @Override
    public String getOwnName() {
        return "NICKNAME_ACCEPTED_SERVICE_MESSAGE";
    }*/
}