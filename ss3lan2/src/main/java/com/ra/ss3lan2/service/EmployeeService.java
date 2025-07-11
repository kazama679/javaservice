package com.ra.ss3lan2.service;

import com.ra.ss3lan2.model.dto.EmployeeDTO;
import com.ra.ss3lan2.model.entity.Employee;
import com.ra.ss3lan2.repository.EmployeeInfo;
import com.ra.ss3lan2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Page<Employee> getEmployeesWithPagingAndSorting(int page, int size, String sortField, String direction) {
        Sort sort = direction.equalsIgnoreCase("ASC") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return employeeRepository.findAll(pageable);
    }

    public List<EmployeeDTO> getEmployeeDTOs() {
        return employeeRepository.findAllEmployeeDTOs();
    }

    public List<EmployeeInfo> getEmployeeInfoList() {
        return employeeRepository.findAllBy();
    }

    public Page<Employee> findByPhoneNumberContaining(String phone, int page, int size, String sortField, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return employeeRepository.findByPhoneNumberContaining(phone, pageable);
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public void update(Long id, Employee updatedEmployee) {
        Employee existing = findById(id);
        if (existing != null) {
            existing.setName(updatedEmployee.getName());
            existing.setEmail(updatedEmployee.getEmail());
            existing.setPhoneNumber(updatedEmployee.getPhoneNumber());
            existing.setSalary(updatedEmployee.getSalary());
            employeeRepository.save(existing);
        }
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }
}