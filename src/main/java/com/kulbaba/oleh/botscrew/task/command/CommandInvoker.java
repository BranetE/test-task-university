package com.kulbaba.oleh.botscrew.task.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.lang.System.out;

@Component
@RequiredArgsConstructor
public class CommandInvoker {
    
    private final List<Command> commands;

    public void handleUserInput(String input) {
        Command command = commands.stream()
                .filter(c -> c.matches(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Can't find command"));

        command.execute(input);
    }
}
