package com.example.order_management.controller;

import com.example.order_management.dto.customer.CustomerSummaryResponse;
import com.example.order_management.dto.customer.CustomerWithOrdersResponse;
import com.example.order_management.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
