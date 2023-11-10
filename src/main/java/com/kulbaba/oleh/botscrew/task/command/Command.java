package com.kulbaba.oleh.botscrew.task.command;

public interface Command {

    Boolean matches(String commandString);
    void execute(String commandString);
}
