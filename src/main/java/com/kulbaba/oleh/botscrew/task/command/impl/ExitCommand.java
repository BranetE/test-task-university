package com.kulbaba.oleh.botscrew.task.command.impl;

import com.kulbaba.oleh.botscrew.task.command.Command;

public class ExitCommand implements Command {
    @Override
    public Boolean matches(String commandString) {
        return commandString.equalsIgnoreCase("exit");
    }

    @Override
    public void execute(String commandString) {
        System.exit(100);
    }
}
