package com.example.order_management.controller;

import com.example.order_management.dto.customer.AllCustomerDetailsResponse;
import com.example.order_management.dto.customer.CustomerDetailResponse;
import com.example.order_management.entity.Customer;
import com.example.order_management.mapper.CustomerMapper;
import com.example.order_management.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerController(
            CustomerRepository customerRepository,
            CustomerMapper customerMapper) {

        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @GetMapping
    public List<AllCustomerDetailsResponse> getAllCustomers() {

        List<Customer> customers = customerRepository.findAll();

        return customerMapper.toAllCustomerDetailsResponse(customers);
    }

    @GetMapping("/{customerId}")
    public CustomerDetailResponse getCustomerById(
            @PathVariable Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        System.out.println("ADDRESS = " + customer.getAddress().getCity());
        return customerMapper.toResponse(customer);
    }
}