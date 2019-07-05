package com.botscrew.botscrew.service.seviceImpl;


import com.botscrew.botscrew.Entity.Employee;
import com.botscrew.botscrew.repository.EmployeeRepository;
import com.botscrew.botscrew.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public Integer countAllByDepartmentName(String departmentName) {
        return repository.countAllByWorkInName(departmentName);
    }

    @Override
    public Employee getByLeaderInDepartment(String departmentName) {
        return repository.getByLeaderName(departmentName);
    }

    @Override
    public List<Employee> getAllByWorkIn(String departmentName) {
        return repository.getAllByWorkInName(departmentName);
    }
}
