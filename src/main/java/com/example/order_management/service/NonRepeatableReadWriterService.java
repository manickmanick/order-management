package com.example.order_management.service;

import com.example.order_management.entity.Order;
import com.example.order_management.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class NonRepeatableReadWriterService {

    private final OrderRepository orderRepository;

    public NonRepeatableReadWriterService(
            OrderRepository orderRepository) {

        this.orderRepository = orderRepository;
    }

    @Transactional
    public void updateOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setAmount(new BigDecimal("5000.00"));

        orderRepository.flush();

        System.out.println(
                "Transaction B - Updated amount to 5000"
        );

        System.out.println(
                "Transaction B - Transaction will commit"
        );
    }
}
