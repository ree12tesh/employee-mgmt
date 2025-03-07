package com.test.employess.service;

import com.test.employess.entity.Employees;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.test.employess.util.ProjectUtil.getEmployeesFromFile;
import static com.test.employess.util.ProjectUtil.saveEmployeesToFile;

@Service
public class EmployeeService {
    public Integer persistEmployees(Employees employees) throws IOException {
        List<Employees> employeeList = getEmployeesFromFile();
        employees.setId(employeeList.size() + 1);
        employeeList.add(employees);
        return saveEmployeesToFile(employeeList);
    }

    public Employees getEmployeeById(int id) throws IOException {
        List<Employees> employeeList = getEmployeesFromFile();
        return employeeList.stream().filter(employees -> employees.getId() == id)
                .findFirst().orElse(null);

    }

    public List<Employees> getEmployeesBySalaryRange(String name, Double fromSalary, Double toSalary) throws IOException {
        return getEmployeesFromFile().stream()
                .filter(emp -> (name == null || emp.getFirstName().contains(name) || emp.getLastName().contains(name)))
                .filter(emp -> (fromSalary == null || emp.getSalary() >= fromSalary))
                .filter(emp -> (toSalary == null || emp.getSalary() <= toSalary))
                .toList();
    }
}

