package com.kulbaba.oleh.botscrew.task.command.impl;

import com.kulbaba.oleh.botscrew.task.command.Command;
import com.kulbaba.oleh.botscrew.task.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.lang.System.out;

@Component
@RequiredArgsConstructor
public class ShowEmployeeCountCommand implements Command {

    private final UniversityService universityService;

    @Override
    public Boolean matches(String commandString) {
        return commandString.startsWith("Show count of employee for");
    }

    @Override
    public void execute(String commandString) {
        String departmentName = commandString.substring("Show count of employee for ".length()).trim();
        Integer employeeCount = universityService.getEmployeeCountByDepartment(departmentName);
        out.println(employeeCount);
    }
}
