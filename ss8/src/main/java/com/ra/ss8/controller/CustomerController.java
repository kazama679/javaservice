package com.ra.ss8.controller;

import com.ra.ss8.model.entity.Customer;
import com.ra.ss8.model.resonse.ApiResponseBuilder;
import com.ra.ss8.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Customer customer) {
        Customer created = customerService.createCustomer(customer);
        return ResponseEntity.ok(ApiResponseBuilder.success("Customer created", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updated = customerService.updateCustomer(id, customer);
        return ResponseEntity.ok(ApiResponseBuilder.success("Customer updated", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(ApiResponseBuilder.success("Customer deleted"));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(ApiResponseBuilder.success("Customer list", customers));
    }
}
