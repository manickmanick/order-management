package com.example.order_management.repository;

import com.example.order_management.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByAmountGreaterThan(BigDecimal amount);

    List<Order> findByAmountBetween(BigDecimal min,BigDecimal max);
}
