package com.example.order_management.service;

import com.example.order_management.dto.customer.CustomerSummaryResponse;
import com.example.order_management.dto.customer.CustomerWithOrdersResponse;
import com.example.order_management.entity.Customer;
import com.example.order_management.mapper.CustomerMapper;
import com.example.order_management.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

    @Transactional(readOnly = true)
    public Page<CustomerSummaryResponse> getCustomers(Pageable pageable){
        Page<Customer> customers = customerRepository.findAll(pageable);

        return customers.map(customerMapper::toSummaryResponse);
    }

    @Transactional(readOnly = true)
    public Page<CustomerWithOrdersResponse> getCustomersWithOrders(
            Pageable pageable) {

        Page<Customer> customers =
                customerRepository.findCustomersWithOrders(pageable);

        return customers.map(customerMapper::toWithOrdersResponse);
    }

    @Transactional(readOnly = true)
    public void testNPlusOne() {

        List<Customer> customers = customerRepository.findAll();

        for (Customer customer : customers) {
            System.out.println(
                    "Customer ID: " + customer.getId()
                            + ", Orders: " + customer.getOrders().size()
            );
        }
    }

    @Transactional(readOnly = true)
    public Slice<CustomerSummaryResponse> getCustomers2(Pageable pageable){
        Slice<Customer> customers = customerRepository.findBy(pageable);

        return customers.map(customerMapper::toSummaryResponse);

    }

   public CustomerSummaryResponse findCustomerByEmailNative(String email){
       return customerRepository
               .findCustomerByEmailNative(email)
               .map(customerMapper::toSummaryResponse)
               .orElseThrow(() ->
                       new RuntimeException("Customer with this email was not found"));

    }

    public List<CustomerSummaryResponse> findCustomerSummaries(){
        return customerRepository.findCustomerSummaries();
    }

}
