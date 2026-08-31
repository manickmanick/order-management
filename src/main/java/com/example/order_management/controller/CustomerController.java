package com.example.order_management.controller;


import com.example.order_management.dto.customer.AllCustomerDetailsResponse;
import com.example.order_management.dto.customer.CustomerDetailResponse;
import com.example.order_management.entity.Customer;
import com.example.order_management.mapper.CustomerMapper;
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
    public List<AllCustomerDetailsResponse> getAllCustomers(){
        List<Customer> customers = customerRepository.findAll();
        return CustomerMapper.toAllCustomerDetailsMapper(customers);
    }

    @GetMapping("/{customerId}")
    public CustomerDetailResponse getCustomerById(@PathVariable Long customerId){
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        return CustomerMapper.toResponse(customer);
    }
}
