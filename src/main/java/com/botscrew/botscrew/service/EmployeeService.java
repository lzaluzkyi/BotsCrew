package com.botscrew.botscrew.service;


import com.botscrew.botscrew.Entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();

    Integer countAllByDepartmentName(String departmentName);

    Employee getByLeaderInDepartment(String departmentName);

    List<Employee> getAllByWorkIn(String departmentName);


}
