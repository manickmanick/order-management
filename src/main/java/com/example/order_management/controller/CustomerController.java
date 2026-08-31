package com.example.order_management.controller;


import com.example.order_management.entity.Customer;
import com.example.order_management.repository.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {


    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable Long customerId){
        return customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}
