package com.example.order_management.service;

import com.example.order_management.entity.Customer;
import com.example.order_management.entity.Order;
import com.example.order_management.repository.CustomerRepository;
import com.example.order_management.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class OrderService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final AuditService auditService;

    public OrderService(
            CustomerRepository customerRepository,
            OrderRepository orderRepository,
            AuditService auditService) {

        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.auditService = auditService;
    }


    @Transactional
    public void placeOrder(Long customerId,boolean failAfterAudit){


        //1.find customer
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()->new RuntimeException("Customer not found"));


        //2.create order
        Order order = new Order();
        order.setOrderNumber("ORD-" + System.currentTimeMillis());
        order.setAmount(new BigDecimal("3000.00"));
        order.setCustomer(customer);

        orderRepository.save(order);

        System.out.println("order saved");


            auditService.saveAudit(
                    "ORDER_CREATED",
                    "Order created for customer " + customerId
            );


        System.out.println("Continuing outer transaction");

        // 4. Optional failure
//        if (failAfterAudit) {
//            throw new RuntimeException("Something failed after audit");
//        }

    }


}
