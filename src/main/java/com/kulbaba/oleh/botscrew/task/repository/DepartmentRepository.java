package com.kulbaba.oleh.botscrew.task.repository;

import com.kulbaba.oleh.botscrew.task.model.Department;
import com.kulbaba.oleh.botscrew.task.repository.projection.DepartmentStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    boolean existsByName(String departmentName);

    @Query(value = "SELECT lector.name FROM department " +
            "JOIN lector ON head_id=lector.id " +
            "WHERE department.name=:departmentName", nativeQuery = true)
    String getHeadOfDepartmentName(@Param("departmentName") String departmentName);

    @Query(value = "SELECT COUNT(*) FROM department_lector " +
            "JOIN department ON department_id=id " +
            "WHERE department.name=:departmentName", nativeQuery = true)
    Integer countEmployeesInDepartment(@Param("departmentName") String departmentName);

    @Query(value = "SELECT AVG(lector.salary) FROM department_lector " +
            "JOIN lector on lector.id = department_lector.lector_id " +
            "JOIN department on department.id = department_lector.department_id " +
            "WHERE department.name=:departmentName", nativeQuery = true)
    Double calculateAverageSalaryByDepartment(@Param("departmentName") String departmentName);

    @Query(value = "SELECT lector.degree as degree, COUNT(*) as total FROM department_lector " +
            "JOIN department on department.id = department_lector.department_id " +
            "JOIN lector on lector.id = department_lector.lector_id " +
            "WHERE department.name = :departmentName " +
            "GROUP BY lector.degree", nativeQuery = true)
    List<DepartmentStatistic> getDepartmentStatistics(@Param("departmentName") String departmentName);
}
