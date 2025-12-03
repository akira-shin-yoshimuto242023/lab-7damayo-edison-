package com.damayo.lab_7.services;

import com.damayo.lab_7.entities.Customer;
import com.damayo.lab_7.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        initializeSampleData();
    }

    private void initializeSampleData() {
        if (customerRepository.count() == 0) {
            customerRepository.save(new Customer(null, "Kenji Renewell", "rken@email.com", null));
            customerRepository.save(new Customer(null, "Marry jane Smith", "Mjane@email.com", null));
            System.out.println("Sample customers initialized in database");
        }
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Transactional
    public Customer createCustomer(Customer customer) {
        validateCustomer(customer);
        customer.setId(null);
        return customerRepository.save(customer);
    }

    private void validateCustomer(Customer customer) {
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer email cannot be empty");
        }
    }
}