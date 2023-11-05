package com.example.botscrew.task.util;

import com.example.botscrew.task.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.*;

@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final UniversityService universityService;

    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(in);
        out.println("\n===============================");
        out.println("=========Enter command=========");
        out.println("===============================");
        while (sc.hasNext()) {
            try {
                handleUserInput(sc.nextLine());
            }catch (RuntimeException e){
                out.println(e.getMessage());
            }
        }
    }

    private void handleUserInput(String input) {
        if (input.startsWith("Who is head of department ")){
            String departmentName = input.substring("Who is head of department ".length()).trim();
            String headOfDepartment = universityService.getHeadOfDepartment(departmentName);
            out.println("Head of " + departmentName + " department is " + headOfDepartment);
        } else if (input.startsWith("Show ") && input.endsWith(" statistics")) {
            String[] parts = input.split(" ");
            if(parts.length == 3){
                String departmentName = parts[1];
                Map<String, Long> statistics = universityService.getDepartmentStatistics(departmentName);
                statistics.forEach((key, value) -> out.println(key.replace("_", " ").toLowerCase() + " - " + value));
            }
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
