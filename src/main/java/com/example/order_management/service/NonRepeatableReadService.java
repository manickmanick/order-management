package com.example.order_management.service;

import com.example.order_management.entity.Order;
import com.example.order_management.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NonRepeatableReadService {

    private final OrderRepository orderRepository;
    private final EntityManager entityManager;

    public NonRepeatableReadService(OrderRepository orderRepository,EntityManager entityManager) {
        this.orderRepository = orderRepository;
        this.entityManager = entityManager;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void readOrderTwice(Long orderId){
        Order firstRead = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));


        System.out.println("Transaction A- First read: " + firstRead.getAmount());

        try{
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        entityManager.clear();

        Order secondRead = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        System.out.println(
                "Transaction A - Second read: "
                        + secondRead.getAmount()
        );
    }

}
