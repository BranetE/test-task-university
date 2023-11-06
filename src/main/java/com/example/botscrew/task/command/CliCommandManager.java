package com.example.botscrew.task.command;

import com.example.botscrew.task.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.lang.System.*;

@Component
@RequiredArgsConstructor
public class CliCommandManager {

    private final UniversityService universityService;

    public void handleUserInput(String input) {
        if (input.startsWith("Who is head of department ")){
            String departmentName = input.substring("Who is head of department ".length()).trim();
            String headOfDepartment = universityService.getHeadOfDepartment(departmentName);
            out.println("Head of " + departmentName + " department is " + headOfDepartment);
        } else if (input.startsWith("Show ") && input.endsWith(" statistics")) {
            LinkedList<String> parts = new LinkedList<>(Arrays.asList(input.split(" ")));
            parts.removeAll(List.of("Show", "statistics"));
            String departmentName = String.join(" ", parts);
            Map<String, Long> statistics = universityService.getDepartmentStatistics(departmentName);
            statistics.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(e -> out.println(e.getKey().replace("_", " ").toLowerCase() + "s" + " - " + e.getValue()));
        } else if (input.startsWith("Show the average salary for the department ")) {
            String departmentName = input.substring("Show the average salary for the department ".length()).trim();
            Double averageSalary = universityService.getAverageSalaryByDepartment(departmentName);
            out.println("The average salary of " + departmentName + " is " + averageSalary);
        } else if (input.startsWith("Show count of employee for ")) {
            String departmentName = input.substring("Show count of employee for ".length()).trim();
            Integer employeeCount = universityService.getEmployeeCountByDepartment(departmentName);
            out.println(employeeCount);
        } else if (input.startsWith("Global search by ")) {
            String template = input.substring("Global search by ".length()).trim();
            List<String> lectors = universityService.getLectorsByTemplate(template);
            out.println(String.join(", ", lectors));
        } else {
            out.println("Invalid input! Please, try again");
        }
    }
}
