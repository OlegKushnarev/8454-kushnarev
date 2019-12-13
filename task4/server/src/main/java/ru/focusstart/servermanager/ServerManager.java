package ru.focusstart.servermanager;

import ru.focusstart.command.Command;

public class ServerManager {
    private Command start;
    private Command stop;

    public ServerManager(Command start, Command stop) {
        this.start = start;
        this.stop = stop;
    }

    public void ServerStart() {
        this.start.execute();
    }

    public void ServerStop() {
        this.stop.execute();
    }
}