package com.kulbaba.oleh.botscrew.task.service.impl;

import com.kulbaba.oleh.botscrew.task.repository.DepartmentRepository;
import com.kulbaba.oleh.botscrew.task.repository.LectorRepository;
import com.kulbaba.oleh.botscrew.task.repository.projection.DepartmentStatistic;
import com.kulbaba.oleh.botscrew.task.service.UniversityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.kulbaba.oleh.botscrew.task.constant.ErrorMessage.DEPARTMENT_NOT_FOUND;
import static com.kulbaba.oleh.botscrew.task.constant.ErrorMessage.LECTORS_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {

    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;

    @Override
    public String getHeadOfDepartment(String departmentName) {
        if(!departmentRepository.existsByName(departmentName)){
            throw new EntityNotFoundException(DEPARTMENT_NOT_FOUND + departmentName);
        }else {
            return departmentRepository.getHeadOfDepartmentName(departmentName);
        }
    }

    @Override
    public Double getAverageSalaryByDepartment(String departmentName) {
        if(!departmentRepository.existsByName(departmentName)){
            throw new EntityNotFoundException(DEPARTMENT_NOT_FOUND + departmentName);
        }else {
            return departmentRepository.calculateAverageSalaryByDepartment(departmentName);
        }
    }

    @Override
    public Map<String, Long> getDepartmentStatistics(String departmentName) {
        if(!departmentRepository.existsByName(departmentName)){
            throw new EntityNotFoundException(DEPARTMENT_NOT_FOUND + departmentName);
        }else {
            return departmentRepository.getDepartmentStatistics(departmentName).stream()
                    .collect(Collectors.toMap(DepartmentStatistic::getDegree, DepartmentStatistic::getTotal));
        }
    }

    @Override
    public Integer getEmployeeCountByDepartment(String departmentName) {
        if(!departmentRepository.existsByName(departmentName)){
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
