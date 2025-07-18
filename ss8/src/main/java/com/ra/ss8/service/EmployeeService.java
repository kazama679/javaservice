package com.ra.ss8.service;

import com.ra.ss8.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);
    Employee update(Long id, Employee employee);
    void delete(Long id);
    List<Employee> getAll();
}