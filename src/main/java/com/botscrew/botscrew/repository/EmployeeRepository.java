package com.botscrew.botscrew.repository;


import com.botscrew.botscrew.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Integer countAllByWorkInName(String departmentName);

    Employee getByLeaderName(String departmentName);

    List<Employee> getAllByWorkInName(String departmentName);
}
