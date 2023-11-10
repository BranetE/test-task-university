package com.kulbaba.oleh.botscrew.task.command.impl;

import com.kulbaba.oleh.botscrew.task.command.Command;
import com.kulbaba.oleh.botscrew.task.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

@Component
@RequiredArgsConstructor
public class ShowDepartmentStatisticsCommand implements Command {

    private final UniversityService universityService;

    @Override
    public Boolean matches(String commandString) {
        return commandString.startsWith("Show ") && commandString.endsWith(" statistics");
    }

    @Override
    public void execute(String commandString) {
        LinkedList<String> parts = new LinkedList<>(Arrays.asList(commandString.split(" ")));
        parts.removeAll(List.of("Show", "statistics"));
        String departmentName = String.join(" ", parts);
        Map<String, Long> statistics = universityService.getDepartmentStatistics(departmentName);
        statistics.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> out.println(e.getKey().replace("_", " ").toLowerCase() + "s" + " - " + e.getValue()));
    }
}
