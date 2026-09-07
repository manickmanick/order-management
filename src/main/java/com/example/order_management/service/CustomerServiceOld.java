package com.example.order_management.service;

import com.example.order_management.dto.customer.CreateCustomer;
import com.example.order_management.entity.Address;
import com.example.order_management.entity.Customer;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceOld {

    private final EntityManager entityManager;

    public CustomerServiceOld(EntityManager entityManager){
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
        Address address = new Address();

        customer.setName(body.getName());
        customer.setEmail(body.getEmail());

        address.setStreet(body.getStreet());
        address.setCity(body.getCity());
        address.setState(body.getState());
        address.setCountry(body.getCountry());

        customer.setAddress(address);
        address.setCustomer(customer);

        entityManager.persist(customer);

        entityManager.flush();
    }

    @Transactional
    public void deleteCustomer(Long customerId) {

        Customer customer =
                entityManager.find(Customer.class, customerId);

//        System.out.println(customer);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }

        entityManager.remove(customer);
    }

    @Transactional
    public void deleteAddress(Long customerId){
        Customer customer =
                entityManager.find(Customer.class, customerId);

//        System.out.println(customer);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }

        customer.setAddress(null);
    }

}
