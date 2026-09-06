package com.example.order_management.mapper;

import com.example.order_management.dto.product.ProductResponse;
import com.example.order_management.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {
                CustomerMapper.class,
                AddressMapper.class
        })
public interface ProductMapper {

    ProductResponse toResponse(Product product);
}
