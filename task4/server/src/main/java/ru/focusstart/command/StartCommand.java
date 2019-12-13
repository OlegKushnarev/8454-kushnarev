package ru.focusstart.command;

import ru.focusstart.model.ServerModel;

public class StartCommand extends Command {

    public StartCommand(ServerModel server) {
        super(server);
    }

    @Override
    public void execute() {
        //this.getServer().start();
    }
}
