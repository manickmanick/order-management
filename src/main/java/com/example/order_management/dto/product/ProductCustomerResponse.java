package com.example.order_management.dto.product;

import com.example.order_management.dto.address.AddressResponse;
import lombok.Data;

@Data
public class ProductCustomerResponse {
    private Long id;
    private String name;
    private String email;
    private AddressResponse address;
}
