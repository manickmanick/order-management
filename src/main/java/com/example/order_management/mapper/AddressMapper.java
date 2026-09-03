package com.example.order_management.mapper;

import com.example.order_management.dto.address.AddressResponse;
import com.example.order_management.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressResponse toResponse(Address address);
}