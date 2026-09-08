package com.example.order_management.dto.customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSummaryResponse {

    private Long id;
    private String name;
    private String email;
}
