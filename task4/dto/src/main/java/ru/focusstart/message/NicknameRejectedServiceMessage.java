package ru.focusstart.message;

public class NicknameRejectedServiceMessage extends ServiceMessage {
    public NicknameRejectedServiceMessage() {
        super("Your nickname rejected.");
        this.setOwnName("NICKNAME_REJECTED_SERVICE_MESSAGE");
    }
}