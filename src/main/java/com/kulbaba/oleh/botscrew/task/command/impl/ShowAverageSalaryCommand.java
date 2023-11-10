package com.kulbaba.oleh.botscrew.task.command.impl;

import com.kulbaba.oleh.botscrew.task.command.Command;
import com.kulbaba.oleh.botscrew.task.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.lang.System.out;

@Component
@RequiredArgsConstructor
public class ShowAverageSalaryCommand implements Command {

    private final UniversityService universityService;

    @Override
    public Boolean matches(String commandString) {
        return commandString.startsWith("Show the average salary for the department");
    }

    @Override
    public void execute(String commandString) {
        String departmentName = commandString.substring("Show the average salary for the department ".length()).trim();
        Double averageSalary = universityService.getAverageSalaryByDepartment(departmentName);
        out.println("The average salary of " + departmentName + " is " + averageSalary);
    }
}
