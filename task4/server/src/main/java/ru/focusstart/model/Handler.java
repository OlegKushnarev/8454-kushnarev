package ru.focusstart.model;

import ru.focusstart.connection.ConnectionParameter;
import ru.focusstart.jsonobject.JSONObject;
import ru.focusstart.serialization.JSONDeserialization;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class Handler implements Runnable {
    BlockingQueue<ConnectionParameter> processConnections;

    public Handler(BlockingQueue<ConnectionParameter> processConnections) {
        this.processConnections = processConnections;
    }

    public JSONObject getJSONObject(String message) {
        if (message != null && !message.isEmpty()) {
            JSONDeserialization deserialization = new JSONDeserialization();
            return deserialization.deserialize(message);
        }
        return null;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                ConnectionParameter connectionParameter = this.processConnections.take();
                BufferedReader reader = connectionParameter.getReader();
                if (reader != null && reader.ready()) {
                    String str = reader.readLine();
                    if (str != null && !str.isEmpty()) {
                        JSONObject jsonObject = this.getJSONObject(str);
                        if (jsonObject != null) {
                            connectionParameter.setJsonObject(jsonObject);
                            ConnectionHandler connectionHandler = ConnectionHandlers.valueOf(jsonObject.getOwnName()).getConnectionHandler();
                            connectionHandler.handle(connectionParameter);
                        }
                    }
                }
            }
        } catch (InterruptedException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
