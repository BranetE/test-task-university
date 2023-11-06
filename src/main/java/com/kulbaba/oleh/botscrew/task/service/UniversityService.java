package com.kulbaba.oleh.botscrew.task.service;

import java.util.List;
import java.util.Map;

public interface UniversityService {
    String getHeadOfDepartment(String departmentName);
    Double getAverageSalaryByDepartment(String departmentName);
    Map<String, Long> getDepartmentStatistics(String departmentName);
    Integer getEmployeeCountByDepartment(String departmentName);
    List<String> getLectorsByTemplate(String template);
}
