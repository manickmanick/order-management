package com.example.order_management.dto.customer;

import com.example.order_management.dto.product.ProductSummaryResponse;
import lombok.Data;

import java.util.List;

@Data
public class CustomerWithProductsResponse {

    private Long id;
    private String name;
    private String email;
    private List<ProductSummaryResponse> products;
}
