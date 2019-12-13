package ru.focusstart.command;

import ru.focusstart.model.ServerModel;

public abstract class Command {
    private ServerModel server;

    public Command(ServerModel server) {
        this.server = server;
    }

    public ServerModel getServer() {
        return server;
    }

    public abstract void execute();
}

