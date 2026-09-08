package com.example.order_management.service;


import com.example.order_management.entity.Order;
import com.example.order_management.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class DirtyReadWriterService {

    private final OrderRepository orderRepository;

    public DirtyReadWriterService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void updateOrderAndWait(Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new RuntimeException("Order not found"));

        order.setAmount(new BigDecimal("5000"));
        orderRepository.flush();
        System.out.println("Transaction B: amount changed to 5000 and flushed");

        try{
            Thread.sleep(10000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }

        System.out.println("Transaction B: committing");
        throw new RuntimeException("error occured");
    }


}
