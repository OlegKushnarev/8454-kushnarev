package ru.focusstart.connection;

import ru.focusstart.jsonobject.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionParameter {
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    String nickname;
    String jsonstring;
    JSONObject jsonObject;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public void setJsonstring(String jsonstring) {
        this.jsonstring = jsonstring;
    }

    public Socket getSocket() {
        return socket;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public String getNickname() {
        return nickname;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public String getJsonstring() {
        return jsonstring;
    }

    public void close() {
        try {
            this.socket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}