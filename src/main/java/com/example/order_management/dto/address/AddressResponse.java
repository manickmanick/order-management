package com.example.order_management.dto.address;

import lombok.Data;

@Data
public class AddressResponse {

    private String street;
    private String city;
    private String state;
    private String country;
}
