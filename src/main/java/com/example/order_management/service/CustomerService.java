package com.example.order_management.service;

import com.example.order_management.dto.customer.CustomerSummaryResponse;
import com.example.order_management.dto.customer.CustomerWithOrdersResponse;
import com.example.order_management.entity.Customer;
import com.example.order_management.mapper.CustomerMapper;
import com.example.order_management.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(
            CustomerRepository customerRepository,
            CustomerMapper customerMapper) {

        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    //1.normal findAll
    @Transactional(readOnly = true)
    public List<CustomerSummaryResponse> getAllCustomers(){
        List<Customer> customers = customerRepository.findAllCustomers();

        return customers.stream()
                .map(customerMapper::toSummaryResponse)
                .toList();


    }

    // 2. Normal INNER JOIN
    @Transactional(readOnly = true)
    public List<CustomerWithOrdersResponse>
    getCustomersUsingJoin() {

        List<Customer> customers =
                customerRepository.findCustomersWithOrdersUsingJoin();

        return customers.stream()
                .map(customerMapper::toWithOrdersResponse)
                .toList();
    }


    // 3. INNER JOIN FETCH
    @Transactional(readOnly = true)
    public List<CustomerWithOrdersResponse>
    getCustomersWithOrders() {

        List<Customer> customers =
                customerRepository.findCustomersWithOrders();

        return customers.stream()
                .map(customerMapper::toWithOrdersResponse)
                .toList();
    }


    // 4. LEFT JOIN FETCH
    @Transactional(readOnly = true)
    public List<CustomerWithOrdersResponse>
    getAllCustomersWithOrders() {

        List<Customer> customers =
                customerRepository.findAllCustomersWithOrders();

        return customers.stream()
                .map(customerMapper::toWithOrdersResponse)
                .toList();
    }


    // 5. Customers with large orders
    @Transactional(readOnly = true)
    public List<CustomerWithOrdersResponse>
    getCustomersWithLargeOrders(BigDecimal amount) {

        List<Customer> customers =
                customerRepository.findCustomersWithLargeOrders(amount);

        return customers.stream()
                .map(customerMapper::toWithOrdersResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CustomerWithOrdersResponse> findAllCustomersUsingEntityGraph(){
        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(customerMapper::toWithOrdersResponse)
                .toList();
    }


}
