package com.example.order_management.dto.customer;
import lombok.Data;


@Data
public class CustomerSummaryResponse {

    private Long id;
    private String name;
    private String email;
}
