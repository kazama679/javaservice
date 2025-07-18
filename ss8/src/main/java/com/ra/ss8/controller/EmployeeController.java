package com.ra.ss8.controller;

import com.ra.ss8.model.entity.Employee;
import com.ra.ss8.model.resonse.ApiResponse;
import com.ra.ss8.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> create(@RequestBody Employee employee) {
        Employee saved = employeeService.create(employee);
        return ResponseEntity.ok(ApiResponse.<Employee>builder()
                .message("Created successfully")
                .data(saved)
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> update(@PathVariable Long id, @RequestBody Employee employee) {
        Employee updated = employeeService.update(id, employee);
        return ResponseEntity.ok(ApiResponse.<Employee>builder()
                .message("Updated successfully")
                .data(updated)
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .message("Deleted successfully")
                .data(null)
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Employee>>> getAll() {
        List<Employee> employees = employeeService.getAll();
        return ResponseEntity.ok(ApiResponse.<List<Employee>>builder()
                .message("Fetched successfully")
                .data(employees)
                .build());
    }
}
