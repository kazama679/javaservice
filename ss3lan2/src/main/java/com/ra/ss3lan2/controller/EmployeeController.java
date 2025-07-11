package com.ra.ss3lan2.controller;

import com.ra.ss3lan2.model.entity.Employee;
import com.ra.ss3lan2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/add";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute("employee") Employee employee) {
        employee.setCreatedAt(LocalDateTime.now());
        employeeService.save(employee);
        return "redirect:/employees";
    }

    @GetMapping
    public String listEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) String phone,
            Model model) {
        Page<Employee> employeePage = (phone != null && !phone.isEmpty())
                ? employeeService.findByPhoneNumberContaining(phone, page, size, sortField, direction)
                : employeeService.getEmployeesWithPagingAndSorting(page, size, sortField, direction);
        model.addAttribute("employeePage", employeePage);
        model.addAttribute("currentPage", page);
        model.addAttribute("phone", phone);
        return "employee/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            return "redirect:/employees";
        }
        model.addAttribute("employee", employee);
        return "employee/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") Employee updatedEmployee) {
        employeeService.update(id, updatedEmployee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
        return "redirect:/employees";
    }
}