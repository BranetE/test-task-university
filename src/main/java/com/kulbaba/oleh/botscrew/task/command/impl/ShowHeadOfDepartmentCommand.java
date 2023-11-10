package com.kulbaba.oleh.botscrew.task.command.impl;

import com.kulbaba.oleh.botscrew.task.command.Command;
import com.kulbaba.oleh.botscrew.task.service.UniversityService;
import com.kulbaba.oleh.botscrew.task.service.impl.UniversityServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.System.out;

@Component
@RequiredArgsConstructor
public class ShowHeadOfDepartmentCommand implements Command {

    private final UniversityServiceImpl universityService;

    @Override
    public Boolean matches(String commandString) {
        return commandString.startsWith("Who is head of department ");
    }

    @Override
    public void execute(String commandString) {
        String departmentName = commandString.substring("Who is head of department ".length()).trim();
        String headOfDepartment = universityService.getHeadOfDepartment(departmentName);
        out.println("Head of " + departmentName + " department is " + headOfDepartment);
    }
}
