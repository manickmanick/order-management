package com.example.order_management.dto.customer;

import com.example.order_management.dto.order.OrderResponse;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDetailResponse {
    private Long id;
    private String name;
    private String email;

    private List<OrderResponse> orders;
}
