package ru.focusstart.connection;

import ru.focusstart.jsonobject.JSONObject;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionParameterBuilder {
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    String nickname;
    JSONObject jsonObject;

    public ConnectionParameterBuilder buildSocket(Socket socket) {
        this.socket = socket;
        return this;
    }

    public ConnectionParameterBuilder buildReader(BufferedReader reader) {
        this.reader = reader;
        return this;
    }

    public ConnectionParameterBuilder buildWriter(PrintWriter writer) {
        this.writer = writer;
        return this;
    }

    public ConnectionParameterBuilder buildNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ConnectionParameterBuilder buildJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        return this;
    }

    public ConnectionParameter build() {
        ConnectionParameter connectionParameter = new ConnectionParameter();
        connectionParameter.setSocket(socket);
        connectionParameter.setReader(reader);
        connectionParameter.setWriter(writer);
        connectionParameter.setNickname(nickname);
        //connectionParameter.setJsonObject(jsonObject);
        return connectionParameter;
    }
}
