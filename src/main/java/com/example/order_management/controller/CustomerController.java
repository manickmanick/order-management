package com.example.order_management.controller;

import com.example.order_management.dto.customer.CustomerSummaryProjection;
import com.example.order_management.dto.customer.CustomerSummaryResponse;
import com.example.order_management.dto.customer.CustomerWithOrdersResponse;
import com.example.order_management.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    // 1. Normal JPQL
    @GetMapping("/jpql")
    public List<CustomerSummaryResponse> getAllCustomers() {

        return customerService.getAllCustomers();
    }


    // 2. INNER JOIN
    @GetMapping("/join/orders")
    public List<CustomerWithOrdersResponse>
    getCustomersUsingJoin() {

        return customerService.getCustomersUsingJoin();
    }


    // 3. INNER JOIN FETCH
    @GetMapping("/join-fetch/orders")
    public List<CustomerWithOrdersResponse>
    getCustomersWithOrders() {

        return customerService.getCustomersWithOrders();
    }


    // 4. LEFT JOIN FETCH
    @GetMapping("/left-join-fetch/orders")
    public List<CustomerWithOrdersResponse>
    getAllCustomersWithOrders() {

        return customerService.getAllCustomersWithOrders();
    }


    // 5. WHERE + JOIN
    @GetMapping("/large-orders")
    public List<CustomerWithOrdersResponse>
    getCustomersWithLargeOrders(
            @RequestParam BigDecimal amount) {

        return customerService.getCustomersWithLargeOrders(amount);
    }

    @GetMapping("/findAllCustomersUsingEntityGraph")
    public List<CustomerWithOrdersResponse> findAllCustomersUsingEntityGraph(){
        return customerService.findAllCustomersUsingEntityGraph();
    }

    @GetMapping("/pagination")
    public Page<CustomerSummaryResponse> getCustomers(
          @PageableDefault(size=20) Pageable pageable
    ){
    return customerService.getCustomers(pageable);
    }

    @GetMapping("/pagination/orders")
    public Page<CustomerWithOrdersResponse> getCustomersWithOrders(
            @PageableDefault(size = 2) Pageable pageable) {

        return customerService.getCustomersWithOrders(pageable);
    }

    @GetMapping("/batch-test")
    public String testBatchFetching() {

        customerService.testNPlusOne();

        return "Check the console for SQL queries";
    }

    @GetMapping("/slice")
    public Slice<CustomerSummaryResponse> getCustomers2(
            @PageableDefault(size = 10) Pageable pageable){
        return customerService.getCustomers2(pageable);
    }

    @GetMapping("/native/{email}")
    public CustomerSummaryResponse findCustomerByEmailNative(@PathVariable("email") String email){
        return customerService.findCustomerByEmailNative(email);
    }

    @GetMapping("/findCustomerSummaries")
    public List<CustomerSummaryResponse> findCustomerSummaries(){
        return customerService.findCustomerSummaries();
    }

    @GetMapping("/findCustomerSummariesUsingProjection")
    public List<CustomerSummaryProjection> findCustomerSummariesUsingProjection(){
        return customerService.findCustomerSummariesUsingProjection();
    }
}
