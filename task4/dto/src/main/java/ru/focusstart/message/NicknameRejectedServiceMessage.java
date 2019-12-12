package ru.focusstart.message;

public class NicknameRejectedServiceMessage extends ServiceMessage {
    public NicknameRejectedServiceMessage() {
        super();
        this.setOwnName("NICKNAME_REJECTED_SERVICE_MESSAGE");
    }
/*
    @Override
    public String getOwnName() {
        return "NICKNAME_REJECTED_SERVICE_MESSAGE";
    }*/
}