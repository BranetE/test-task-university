package com.example.botscrew.task.service.impl;

import com.example.botscrew.task.repository.DepartmentRepository;
import com.example.botscrew.task.repository.LectorRepository;
import com.example.botscrew.task.service.UniversityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {

    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;

    @Override
    public String getHeadOfDepartment(String departmentName) {
        if(Boolean.FALSE.equals(departmentRepository.existsByName(departmentName))){
            throw new EntityNotFoundException("Can't find department with the name: " + departmentName);
        }else {
            return departmentRepository.getHeadOfDepartmentName(departmentName);
        }
    }

    @Override
    public Double getAverageSalaryByDepartment(String departmentName) {
        if(Boolean.FALSE.equals(departmentRepository.existsByName(departmentName))){
            throw new EntityNotFoundException("Can't find department with the name: " + departmentName);
        }else {
            return departmentRepository.calculateAverageSalaryByDepartment(departmentName);
//            System.out.println("The average salary of " + departmentName + " is " + averageSalaryByDepartment);
        }
    }

    @Override
    public Map<String, Long> getDepartmentStatistics(String departmentName) {
        if(Boolean.FALSE.equals(departmentRepository.existsByName(departmentName))){
            throw new EntityNotFoundException("Can't find department with the name: " + departmentName);
        }else {
            return departmentRepository.getDepartmentStatistics(departmentName).stream()
                    .collect(Collectors.toMap(e -> (String) e[0], e -> (Long) e[1]));
//            System.out.println("Statistics for " + departmentName + " department:");
//            departmentStatistics
//                    .forEach(
//                            e -> System.out.println(e[0].toString().toLowerCase() + "s - " + e[1])
//                    );
        }
    }

    @Override
    public Integer getEmployeeCountByDepartment(String departmentName) {
        if(Boolean.FALSE.equals(departmentRepository.existsByName(departmentName))){
            throw new EntityNotFoundException("Can't find department with the name: " + departmentName);
        }else {
            return departmentRepository.countEmployeesInDepartment(departmentName);
//            System.out.println(employeeCountByDepartment);
        }
    }

    @Override
    public List<String> getLectorsByTemplate(String template) {
        return lectorRepository.findLectorsByNameContaining(template);
//        lectorNames.forEach(
//                e -> System.out.println(e + ", ")
//        );
    }
}
