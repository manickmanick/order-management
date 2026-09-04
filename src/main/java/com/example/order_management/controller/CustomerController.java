package com.example.order_management.controller;

import com.example.order_management.dto.customer.AllCustomerDetailsResponse;
import com.example.order_management.dto.customer.CustomerDetailResponse;
import com.example.order_management.dto.customer.UpdateCustomerNameRequest;
import com.example.order_management.entity.Customer;
import com.example.order_management.mapper.CustomerMapper;
import com.example.order_management.repository.CustomerRepository;
import com.example.order_management.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerService customerService;

    public CustomerController(
            CustomerRepository customerRepository,
             CustomerService customerService,
            CustomerMapper customerMapper
            ) {

        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.customerService = customerService;
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

    @PutMapping("/{customerId}/name")
    public String updateName(@PathVariable Long customerId,@RequestBody UpdateCustomerNameRequest request){
        customerService.updateCustomerName(
                customerId,
                request.getName()
        );
        return "updated";
    }

    @GetMapping("/{customerId}/mergeLearning")
    public String mergeLearning(@PathVariable Long customerId){
        customerService.mergeLearning(customerId);
        return "checking merge learning";
    }

    @GetMapping("/{customerId}/flush")
    public String testFlush(@PathVariable Long customerId){
        customerService.testFlush(customerId);
        return "Checking flushing";
    }

    @GetMapping("/{customerId}/rollbackLearning")
    public String testTransactional(@PathVariable Long customerId){
        customerService.testTransactional(customerId);
        return "Learning rollback exeeption";
    }

}