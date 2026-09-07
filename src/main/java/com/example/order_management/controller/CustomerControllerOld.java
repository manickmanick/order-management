//package com.example.order_management.controller;
//
//import com.example.order_management.dto.customer.AllCustomerDetailsResponse;
//import com.example.order_management.dto.customer.CreateCustomer;
//import com.example.order_management.dto.customer.CustomerDetailResponse;
//import com.example.order_management.dto.customer.UpdateCustomerNameRequest;
//import com.example.order_management.entity.Customer;
//import com.example.order_management.mapper.CustomerMapper;
//import com.example.order_management.repository.CustomerRepository;
//import com.example.order_management.service.CustomerServiceOld;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/customers")
//public class CustomerControllerOld {
//
//    private final CustomerRepository customerRepository;
//    private final CustomerMapper customerMapper;
//    private final CustomerServiceOld customerServiceOld;
//
//    public CustomerControllerOld(
//            CustomerRepository customerRepository,
//             CustomerServiceOld customerServiceOld,
//            CustomerMapper customerMapper
//            ) {
//
//        this.customerRepository = customerRepository;
//        this.customerMapper = customerMapper;
//        this.customerServiceOld = customerServiceOld;
//    }
//
//    @GetMapping
//    public List<AllCustomerDetailsResponse> getAllCustomers() {
//
//        List<Customer> customers = customerRepository.findAll();
//
//        return customerMapper.toAllCustomerDetailsResponse(customers);
//    }
//
//    @GetMapping("/{customerId}")
//    public CustomerDetailResponse getCustomerById(
//            @PathVariable Long customerId) {
//
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() ->
//                        new RuntimeException("Customer not found"));
//
//        System.out.println("ADDRESS = " + customer.getAddress().getCity());
//        return customerMapper.toResponse(customer);
//    }
//
//    @PutMapping("/{customerId}/name")
//    public String updateName(@PathVariable Long customerId,@RequestBody UpdateCustomerNameRequest request){
//        customerServiceOld.updateCustomerName(
//                customerId,
//                request.getName()
//        );
//        return "updated";
//    }
//
//    @GetMapping("/{customerId}/mergeLearning")
//    public String mergeLearning(@PathVariable Long customerId){
//        customerServiceOld.mergeLearning(customerId);
//        return "checking merge learning";
//    }
//
//    @GetMapping("/{customerId}/flush")
//    public String testFlush(@PathVariable Long customerId){
//        customerServiceOld.testFlush(customerId);
//        return "Checking flushing";
//    }
//
//    @GetMapping("/{customerId}/rollbackLearning")
//    public String testTransactional(@PathVariable Long customerId){
//        customerServiceOld.testTransactional(customerId);
//        return "Learning rollback exeeption";
//    }
//
//    @PostMapping("/createCustomer")
//    public String createCustomer(@RequestBody CreateCustomer body){
//        customerServiceOld.createCustomer(body);
//        return "Customer was created.";
//    }
//
//    @GetMapping("/{customerId}/delete")
//    public String deleteCustomer(@PathVariable  Long customerId){
//        customerServiceOld.deleteCustomer(customerId);
//        return "user was deleted successfully";
//    }
//
//    @GetMapping("/{customerId}/deleteAddress")
//    public String deleteAddress(@PathVariable  Long customerId){
//        customerServiceOld.deleteAddress(customerId);
//        return "deleted address successfully.";
//    }
//
//}