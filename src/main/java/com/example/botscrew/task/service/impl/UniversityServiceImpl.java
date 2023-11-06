package com.example.botscrew.task.service.impl;

import com.example.botscrew.task.repository.DepartmentRepository;
import com.example.botscrew.task.repository.LectorRepository;
import com.example.botscrew.task.service.UniversityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.botscrew.task.constant.ErrorMessage.DEPARTMENT_NOT_FOUND;
import static com.example.botscrew.task.constant.ErrorMessage.LECTORS_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {

    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;

    @Override
    public String getHeadOfDepartment(String departmentName) {
        if(Boolean.FALSE.equals(departmentRepository.existsByName(departmentName))){
            throw new EntityNotFoundException(DEPARTMENT_NOT_FOUND + departmentName);
        }else {
            return departmentRepository.getHeadOfDepartmentName(departmentName);
        }
    }

    @Override
    public Double getAverageSalaryByDepartment(String departmentName) {
        if(Boolean.FALSE.equals(departmentRepository.existsByName(departmentName))){
            throw new EntityNotFoundException(DEPARTMENT_NOT_FOUND + departmentName);
        }else {
            return departmentRepository.calculateAverageSalaryByDepartment(departmentName);
        }
    }

    @Override
    public Map<String, Long> getDepartmentStatistics(String departmentName) {
        if(Boolean.FALSE.equals(departmentRepository.existsByName(departmentName))){
            throw new EntityNotFoundException(DEPARTMENT_NOT_FOUND + departmentName);
        }else {
            return departmentRepository.getDepartmentStatistics(departmentName).stream()
                    .collect(Collectors.toMap(e -> (String) e[0], e -> (Long) e[1]));
        }
    }

    @Override
    public Integer getEmployeeCountByDepartment(String departmentName) {
        if(Boolean.FALSE.equals(departmentRepository.existsByName(departmentName))){
            throw new EntityNotFoundException(DEPARTMENT_NOT_FOUND + departmentName);
        }else {
            return departmentRepository.countEmployeesInDepartment(departmentName);
        }
    }

    @Override
    public List<String> getLectorsByTemplate(String template) {
        List<String> lectors = lectorRepository.findLectorsByNameContaining(template);
        if(lectors.isEmpty()){
            throw new EntityNotFoundException(LECTORS_NOT_FOUND + template);
        }
        return lectors;
    }
}
