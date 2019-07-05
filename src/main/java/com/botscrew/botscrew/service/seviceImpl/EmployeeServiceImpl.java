package com.botscrew.botscrew.service.seviceImpl;


import com.botscrew.botscrew.Entity.Degree;
import com.botscrew.botscrew.Entity.Employee;
import com.botscrew.botscrew.repository.EmployeeRepository;
import com.botscrew.botscrew.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private EmployeeRepository repository;

    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public List<Degree> getAllDegrees() {
        List<Employee> all = repository.findAll();
        List<Degree> degrees = new ArrayList<>();
        for (Employee employee : all) {
            degrees.add(employee.getDegree());
        }
        return degrees;
    }

    @Override
    public Integer countAllByDepartmentName(String departmentName) {
        return repository.countAllByWorkInName(departmentName);
    }

    @Override
    public List<BigDecimal> getAllSalaryByDepartmentName(String departmentName) {
        return repository.getAllSalaryByWorkInName(departmentName);
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
