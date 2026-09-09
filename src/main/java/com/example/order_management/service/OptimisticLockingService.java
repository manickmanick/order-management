package com.example.order_management.service;


import com.example.order_management.entity.Order;
import com.example.order_management.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class OptimisticLockingService {

    private final OrderRepository orderRepository;

    public OptimisticLockingService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Transactional
    public void updateOrder(Long orderId, BigDecimal amount){

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));


        System.out.println(
                "Thread: " + Thread.currentThread().getName()
                        + " | Read version: " + order.getVersion()
        );

        order.setAmount(amount);

        System.out.println(
                "Thread: " + Thread.currentThread().getName()
                        + " | Updating amount to: " + amount
        );

        orderRepository.flush();

        System.out.println(
                "Thread: " + Thread.currentThread().getName()
                        + " | Update flushed"
        );

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(
                "Thread: " + Thread.currentThread().getName()
                        + " | Transaction finishing"
        );

    }

}
