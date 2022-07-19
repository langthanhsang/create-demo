package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    @Autowired
    private final EmployeeService employeeService;

    @GetMapping("/list")
    public ResponseEntity<Iterable<Employee>> getListEmployees() {
        Iterable<Employee> employees = employeeService.findAll();
        if(!employees.iterator().hasNext()){
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(employees,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee employeeCreate = employeeService.save(employee);
        return new ResponseEntity<>(employeeCreate, HttpStatus.ACCEPTED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employeeEdit, @PathVariable Long id) {
        Optional<Employee> employee = employeeService.findOne(id);
        if(!employee.isPresent()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        employeeEdit.setId(employee.get().getId());
        employeeEdit =employeeService.save(employeeEdit);
        return new ResponseEntity<>(employeeEdit, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Long id)  {
        Optional<Employee> employee = employeeService.findOne(id);
        if(!employee.isPresent()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        employeeService.delete(id);
        return new ResponseEntity<>(employee.get(),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<Employee>> findEmployeeByLead(@PathVariable("id") Long leadName) {
        Iterable<Employee> employees = employeeService.findByLeadName(leadName);
        if (!employees.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
