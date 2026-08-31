package com.example.order_management.dto.customer;

import com.example.order_management.dto.address.AddressResponse;
import lombok.Data;

import java.util.List;

@Data
public class AllCustomerDetailsResponse {

    private Long id;
    private String name;
    private String email;
    private AddressResponse address;

}
