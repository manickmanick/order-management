package com.example.order_management.dto.product;

import com.example.order_management.dto.customer.CreateCustomer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private List<ProductCustomerResponse> customers;
}
