package com.example.order_management.service;

import com.example.order_management.dto.customer.CreateCustomer;
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

    @Transactional
    public void mergeLearning(Long customerId){
       Customer customer = entityManager.find(Customer.class,customerId);

       if(customer == null){
           throw new RuntimeException("Customernot found");
       }
        System.out.println(customer.getName());
       entityManager.detach(customer);
       Customer customer1 = entityManager.merge(customer);
        customer1.setName("manick");
    }

    @Transactional
    public void testFlush(Long customerId){
        Customer customer = entityManager.find(Customer.class,customerId);

        if(customer == null){
            throw new RuntimeException("Customer not found");
        }
        System.out.println("================== before flush ==================");
        customer.setName("manickaperumal");
        entityManager.flush();
        System.out.println("================ after flush ======================");
    }

    @Transactional
    public void testTransactional(Long customerId){
        Customer customer = entityManager.find(Customer.class,customerId);

        if(customer == null){{
            throw new RuntimeException("Customer not found");
        }}

        System.out.println("======= before flush ============");
        customer.setName("manick");
        entityManager.flush();
        System.out.println("======== after flush ===========");

        throw new RuntimeException("error happened");
    }

    @Transactional
    public void createCustomer(CreateCustomer body) {

        Customer customer = new Customer();

        customer.setName(body.getName());
        customer.setEmail(body.getEmail());
//        customer.setAddress(body.getAddressId());

        entityManager.persist(customer);

        entityManager.flush();
    }

}
