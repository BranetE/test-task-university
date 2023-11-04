package com.example.botscrew.task.util;

import com.example.botscrew.task.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class CommandLineInterface implements CommandLineRunner {

    private final UniversityService universityService;

    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter command");
        while (sc.hasNext()) {
            handleUserInput(sc.nextLine());
        }
    }

    private void handleUserInput(String input) {
        if (input.startsWith("Who is head of department ")){
            String departmentName = input.substring("Who is head of department ".length()).trim();
            universityService.showHeadOfDepartment(departmentName);
        } else if (input.startsWith("Show ") && input.endsWith(" statistics")) {
            String[] parts = input.split(" ");
            if(parts.length == 3){
                String departmentName = parts[1];
                universityService.showDepartmentStatistics(departmentName);
            }
        } else if (input.startsWith("Show the average salary for the department ")) {
            String departmentName = input.substring("Show the average salary for the department ".length()).trim();
            universityService.showAverageSalaryByDepartment(departmentName);
        } else if (input.startsWith("Show count of employee for ")) {
            String departmentName = input.substring("Show count of employee for ".length()).trim();
            universityService.showEmployeeCountByDepartment(departmentName);
        } else if (input.startsWith("Global search by ")) {
            String template = input.substring("Global search by ".length()).trim();
            universityService.showLectorsByTemplate(template);
        } else {
            System.out.println("Invalid input! Please, try again");
        }
    }
}
