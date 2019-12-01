package ru.focusstart.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cheсk{
    public static void checkLoginOptions(String serverAddress, String nickName) throws IllegalArgumentException {
        checkServerAddress(serverAddress);
        checkNickName(nickName);
    }

    public static void checkNickName(String nickName) {
        if (nickName.isEmpty()) {
            throw new IllegalArgumentException("Не введён ник!");
        }
    }

    public static void checkServerAddress(String serverAddress) {
        if (serverAddress.isEmpty()) {
            throw new IllegalArgumentException("Не введён адрес сервера!");
        }
    }
}
