package com.ra.ss8.service;

import com.ra.ss8.exception.ResourceNotFoundException;
import com.ra.ss8.model.entity.Customer;
import com.ra.ss8.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        customer.setStatus(true);
        customer.setCreatedAt(LocalDateTime.now());
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer updated) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer không tồn tại với id: " + id));

        customer.setFullName(updated.getFullName());
        customer.setPhone(updated.getPhone());
        customer.setEmail(updated.getEmail());
        customer.setNumberOfPayments(updated.getNumberOfPayments());
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer không tồn tại với id: " + id));
        customer.setStatus(false); // xóa mềm
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer không tồn tại với id: " + id));
    }
}