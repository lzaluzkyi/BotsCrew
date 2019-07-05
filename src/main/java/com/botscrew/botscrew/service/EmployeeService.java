package com.botscrew.botscrew.service;


import com.botscrew.botscrew.Entity.Degree;
import com.botscrew.botscrew.Entity.Employee;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeService {

    Employee save(Employee employee);

    void delete(Integer id);

    List<Employee> getAll();

    List<Degree> getAllDegrees();

    Integer countAllByDepartmentName(String departmentName);

    List<BigDecimal> getAllSalaryByDepartmentName(String departmentName);

    Employee getByLeaderInDepartment(String departmentName);

    List<Employee> getAllByWorkIn(String departmentName);


}
