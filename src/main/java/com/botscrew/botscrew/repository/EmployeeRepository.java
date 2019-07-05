package com.botscrew.botscrew.repository;


import com.botscrew.botscrew.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Integer countAllByWorkInName(String departmentName);

    List<BigDecimal> getAllSalaryByWorkInName(String departmentName);

    Employee getByLeaderName(String departmentName);

    List<Employee> getAllByWorkInName(String departmentName);
}
