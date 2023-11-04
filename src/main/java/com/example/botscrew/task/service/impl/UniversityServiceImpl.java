package com.example.botscrew.task.service.impl;

import com.example.botscrew.task.model.Degree;
import com.example.botscrew.task.repository.DepartmentRepository;
import com.example.botscrew.task.repository.LectorRepository;
import com.example.botscrew.task.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {

    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;

    @Override
    public void showHeadOfDepartment(String departmentName) {
        if(!departmentRepository.existsByName(departmentName)){
            System.out.println("Department with the name of " + departmentName + " doesn't exist");
        }else {
            String headOfDepartment = departmentRepository.getHeadOfDepartmentName(departmentName);
            System.out.println("Head of " + departmentName + " department is " + headOfDepartment);
        }
    }

    @Override
    public void showAverageSalaryByDepartment(String departmentName) {
        if(!departmentRepository.existsByName(departmentName)){
            System.out.println("Department with the name of " + departmentName + " doesn't exist");
        }else {
            String averageSalaryByDepartment = departmentRepository.calculateAverageSalaryByDepartment(departmentName).toString();
            System.out.println("The average salary of " + departmentName + " is " + averageSalaryByDepartment);
        }
    }

    @Override
    public void showDepartmentStatistics(String departmentName) {
        if(!departmentRepository.existsByName(departmentName)){
            System.out.println("Department with the name of " + departmentName + " doesn't exist");
        }else {
            Map<String, Integer> departmentStatistics = departmentRepository.getDepartmentStatistics(departmentName);
            System.out.println("Statistics for " + departmentName + " department:");
//            System.out.println("Assistants - " + departmentStatistics.get(Degree.ASSISTANT.toString()));
//            System.out.println("Associate Professors - " + departmentStatistics.);
//            System.out.println("Professors - " + professorCount);
            departmentStatistics.entrySet()
                    .forEach(
                            e -> {
                                System.out.println(e.getKey() + "s " + e.getValue());
                            }
                    );
        }
    }

    @Override
    public void showEmployeeCountByDepartment(String departmentName) {
        if(!departmentRepository.existsByName(departmentName)){
            System.out.println("Department with the name of " + departmentName + " doesn't exist");
        }else {
            String employeeCountByDepartment = departmentRepository.countEmployeesInDepartment(departmentName).toString();
            System.out.println(employeeCountByDepartment);
        }
    }

    @Override
    public void showLectorsByTemplate(String template) {
        List<String> lectorNames = lectorRepository.findLectorsByNameContaining(template);
        lectorNames.forEach(
                e -> System.out.println(e + ", ")
        );
    }
}
