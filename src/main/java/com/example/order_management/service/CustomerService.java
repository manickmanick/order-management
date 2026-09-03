package com.example.order_management.service;

import com.example.order_management.entity.Customer;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    private final EntityManager entityManager;

    public CustomerService(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    public void updateCustomerName(Long customerId,String name){
        Customer customer = entityManager.find(Customer.class,customerId);

        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }

        System.out.println(customer.getName());
        customer.setName(name);


    }


}
