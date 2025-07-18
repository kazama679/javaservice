package com.ra.ss8.service;

import com.ra.ss8.model.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
}
