package com.example.order_management.service;

import com.example.order_management.entity.Order;
import com.example.order_management.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class DirtyReadReaderService {

    private final OrderRepository orderRepository;

    public DirtyReadReaderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public BigDecimal readOrderAmount(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(
                ()-> new RuntimeException("Order not found")
        );
        System.out.println("Transaction A: amount = " + order.getAmount());

        return order.getAmount();

    }


}
