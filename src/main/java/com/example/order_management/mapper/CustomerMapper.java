package com.example.order_management.mapper;

import com.example.order_management.dto.customer.AllCustomerDetailsResponse;
import com.example.order_management.dto.customer.CustomerDetailResponse;
import com.example.order_management.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                OrderMapper.class,
                AddressMapper.class
        }
)
public interface CustomerMapper {

    CustomerDetailResponse toResponse(Customer customer);

    AllCustomerDetailsResponse toAllCustomerDetailsResponse(Customer customer);

    List<AllCustomerDetailsResponse> toAllCustomerDetailsResponse(
            List<Customer> customers
    );
}