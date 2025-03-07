package com.test.employess.controller;

import com.test.employess.entity.Employees;
import com.test.employess.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Integer>  createEmployee(@RequestBody Employees employee) throws IOException {
        return ResponseEntity.ok(employeeService.persistEmployees(employee));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employees> getEmployeeById(@PathVariable int id) throws IOException {
      return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping("")
    public ResponseEntity<List<Employees>> getEmployees(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) Double fromSalary,
                                        @RequestParam(required = false) Double toSalary) throws IOException {
        return ResponseEntity.ok(employeeService.getEmployeesBySalaryRange(name, fromSalary,toSalary));
    }
}
