package com.kulbaba.oleh.botscrew.task.command.impl;

import com.kulbaba.oleh.botscrew.task.command.Command;
import com.kulbaba.oleh.botscrew.task.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.System.out;

@Component
@RequiredArgsConstructor
public class GlobalSearchCommand implements Command {

    private final UniversityService universityService;

    @Override
    public Boolean matches(String commandString) {
        return commandString.startsWith("Global search by");
    }

    @Override
    public void execute(String commandString) {
        String template = commandString.substring("Global search by".length()).trim();
        List<String> lectors = universityService.getLectorsByTemplate(template);
        out.println(String.join(", ", lectors));
    }
}
