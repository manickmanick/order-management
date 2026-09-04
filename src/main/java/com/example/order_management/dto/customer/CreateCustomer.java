package com.example.order_management.dto.customer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateCustomer {

    public String name;
    public String email;
    public String street;
    public String city;
    public String state;
    public String country;
}
