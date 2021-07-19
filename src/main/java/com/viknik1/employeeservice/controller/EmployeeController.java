package com.viknik1.employeeservice.controller;

import com.viknik1.employeeservice.model.Employee;
import com.viknik1.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String sayHello() {
        return "Hello Employee";
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable("id") long id) {
        return employeeService.getEmployeeById(id).get();
    }

    @PutMapping("employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id")long id,
                                   @RequestBody Employee employee) {
        Employee employee1 = employeeService.getEmployeeById(id).get();
        employee1.setFirstName(employee.getFirstName());
        employee1.setLastName(employee.getLastName());
        employee1.setEmailId(employee.getEmailId());
        return ResponseEntity.ok(employeeService.createEmployee(employee1));
    }

    @DeleteMapping("employees/{id}")
    public Map<String,Boolean> deleteEmployee(@PathVariable("id") long id) {
        employeeService.deleteEmployee(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Employee deleted !",Boolean.TRUE);
        return response;
    }
}
