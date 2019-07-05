package com.botscrew.botscrew.consoleDialog;

import com.botscrew.botscrew.Entity.Degree;
import com.botscrew.botscrew.Entity.Employee;
import com.botscrew.botscrew.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConsoleDialog {

    @Autowired
    private EmployeeService employeeService;

    @PostConstruct
    public void start() throws IOException {

        System.out.println(
                "       HELLO!!!" +
                        "The app implement such commands:\n" +
                        "1. Who is head of department {department_name}\n" +
                        "2. Show {department_name} statistic.\n" +
                        "3. Show the average salary for department {department_name}\n" +
                        "4. Show count of employee for {department_name}.\n" +
                        "5. Global search by {template}.\n");

        startConsoleDialog();
    }

    private void startConsoleDialog() throws IOException {
        boolean run = true;
        while (run) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();
            if (containsIgnoreCase(input, "Who is head of department")) {
                String departmentName = input.replaceAll("(?i)Who is head of department", "").replaceAll("[^a-zA-Z0-9]", "").trim();
                getLeaderOfDepartment(departmentName);
            } else if (containsIgnoreCase(input, "Show") && containsIgnoreCase(input, "statistic")) {
                String departmentName = input.replaceAll("(?i)Show", "").replace("(?i)statistic", "").replaceAll("[^a-zA-Z0-9]", "").trim();
                countDegree(departmentName);
            } else if (containsIgnoreCase(input, "Show the average salary for department")) {
                String departmentName = input.replaceAll("(?i)Show the average salary for department", "").replaceAll("[^a-zA-Z0-9]", "").trim();
                averageSalaryOfDepartment(departmentName);
            } else if (containsIgnoreCase(input, "Show count of employee for")) {
                String departmentName = input.replaceAll("(?i)Show count of employee for", "").replaceAll("[^a-zA-Z0-9]", "").trim();
                countWorkerInDepartment(departmentName);
            } else if (containsIgnoreCase(input, "Global search by")) {
                String searchBy = input.replaceAll("(?i)Global search by", "").replaceAll("[^a-zA-Z0-9]", "").trim();
                globalSearch(searchBy);
            } else if (input.equals("0") || input.equalsIgnoreCase("exit")) {
                run = false;
            } else {
                System.out.println("Incorrect input");
            }
        }
    }

    private void countWorkerInDepartment(String departmentName) {
        if (departmentName.isEmpty()) {
            System.out.println("Department name is empty");
            return;
        }
        int count = employeeService.countAllByDepartmentName(departmentName);
        if (count == 0) {
            System.out.println("Department name " + departmentName + " is incorrect or this department don't have worker");
            return;
        }
        System.out.println("In " + departmentName + " " + count + " worker");
    }

    private void globalSearch(String searchBy) {
        if (searchBy.isEmpty()) {
            System.out.println("Criteria is empty");
            return;
        }
        List<Employee> allEmployees = employeeService.getAll();
        List<String> filteredEmployee = new ArrayList<>();
        for (Employee employee : allEmployees) {
            if (containsIgnoreCase(employee.getName(), searchBy))
                filteredEmployee.add(employee.getName());
        }
        if (filteredEmployee.isEmpty()) {
            System.out.println("No employee which have " + searchBy);
            return;
        }
        System.out.println(filteredEmployee.toString());
    }

    private void averageSalaryOfDepartment(String departmentName) {
        if (departmentName.isEmpty()) {
            System.out.println("Department name is empty");
            return;
        }
        List<Employee> employees = employeeService.getAllByWorkIn(departmentName);
        if (employees.isEmpty()) {
            System.out.println("Department name " + departmentName + " is incorrect or this department don't have worker");
            return;
        }
        List<BigDecimal> salaries = new ArrayList<>();
        for (Employee employee : employees) {
            salaries.add(employee.getSalary());
        }
        if (!salaries.isEmpty()) {
            BigDecimal sumSalaries = new BigDecimal(0);
            for (BigDecimal oneSalary : salaries) sumSalaries = sumSalaries.add(oneSalary);
            BigDecimal result = sumSalaries.divide(new BigDecimal(salaries.size()));
            System.out.println("The average salary of " + departmentName + " is " + result);
        }
    }

    private void getLeaderOfDepartment(String departmentName) {
        if (departmentName.isEmpty()) {
            System.out.println("Department name is empty");
            return;
        }
        Employee leader = employeeService.getByLeaderInDepartment(departmentName);
        if (leader == null) {
            System.out.println("Department name " + departmentName + " is incorrect or this department don't have leader");
            return;
        }
        System.out.println(" Head of " + departmentName + " department is " + leader.getName());
    }

    private void countDegree(String departmentName) {
        if (departmentName.isEmpty()) {
            System.out.println("Department name is empty");
            return;
        }
        List<Employee> employees = employeeService.getAllByWorkIn(departmentName);
        if (employees.isEmpty()) {
            System.out.println("Department name " + departmentName + " is incorrect or this department don't have worker");
            return;
        }
        int assistans = 0;
        int associateProfessors = 0;
        int professors = 0;
        for (Employee employee : employees) {
            if (employee.getDegree().equals(Degree.assistant)) {
                assistans++;
            } else if (employee.getDegree().equals(Degree.associate_professor)) {
                associateProfessors++;
            } else if (employee.getDegree().equals(Degree.professor)) {
                professors++;
            }
        }
        System.out.println("assistans - " + assistans + ". associate professors - " + associateProfessors + ". professors -" + professors);
    }

    public static boolean containsIgnoreCase(String str, String subString) {
        return str.toLowerCase().contains(subString.toLowerCase());
    }
}


