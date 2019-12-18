package ru.focusstart.model;

import ru.focusstart.connection.ConnectionParameter;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class WaitingForMessage implements Runnable {
    BlockingQueue<ConnectionParameter> processConnections;
    List<ConnectionParameter> onLineConnections;

    public WaitingForMessage(BlockingQueue<ConnectionParameter> processConnections, List<ConnectionParameter> onLineConnections) {
        this.processConnections = processConnections;
        this.onLineConnections = onLineConnections;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                if (!onLineConnections.isEmpty()) {
                    for (ConnectionParameter connection :
                            onLineConnections) {
                        if (connection.isReady()) {
                            this.processConnections.put(connection);
                            break;
                        }
                    }

                }
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
