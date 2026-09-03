package com.example.order_management.mapper;

import com.example.order_management.dto.order.OrderResponse;
import com.example.order_management.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponse toResponse(Order order);
}