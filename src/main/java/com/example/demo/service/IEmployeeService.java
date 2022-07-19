package com.example.demo.service;

import com.example.demo.entity.Employee;

import java.util.Iterator;
import java.util.Optional;

public interface IEmployeeService {

    Iterable<Employee> findAll();

    Optional<Employee> findOne(Long id);

    Employee save(Employee employee);

    void delete( Long id);

    Iterable<Employee> findByLeadName(Long id);

}
