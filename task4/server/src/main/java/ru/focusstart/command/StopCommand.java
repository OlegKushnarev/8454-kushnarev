package ru.focusstart.command;

import ru.focusstart.model.ServerModel;

public class StopCommand extends Command {

    public StopCommand(ServerModel server) {
        super(server);
    }

    @Override
    public void execute() {
        this.getServer().stop();
    }
}