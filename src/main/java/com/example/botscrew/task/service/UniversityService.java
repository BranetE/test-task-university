package com.example.botscrew.task.service;

public interface UniversityService {
    void showHeadOfDepartment(String departmentName);
    void showAverageSalaryByDepartment(String departmentName);
    void showDepartmentStatistics(String departmentName);
    void showEmployeeCountByDepartment(String departmentName);
    void showLectorsByTemplate(String template);
}
