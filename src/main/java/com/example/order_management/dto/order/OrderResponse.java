package com.example.order_management.dto.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderResponse {

    private Long id;
    private String orderNumber;
    private BigDecimal amount;
}
