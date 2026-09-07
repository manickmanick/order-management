package com.example.order_management.mapper;

import com.example.order_management.dto.customer.*;
import com.example.order_management.dto.product.ProductCustomerResponse;
import com.example.order_management.dto.product.ProductSummaryResponse;
import com.example.order_management.entity.Customer;
import com.example.order_management.entity.Product;
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

    CustomerSummaryResponse toSummaryResponse(Customer customer);

    CustomerWithOrdersResponse toWithOrdersResponse(Customer customer);

    CustomerWithAddressResponse toWithAddressResponse(Customer customer);

    CustomerWithProductsResponse toWithProductsResponse(Customer customer);

    ProductCustomerResponse toProductCustomerResponse(Customer customer);

    ProductSummaryResponse toProductSummaryResponse(
            Product product
    );

    List<CustomerWithOrdersResponse> toWithOrdersResponse(
            List<Customer> customers
    );

    List<CustomerWithAddressResponse> toWithAddressResponse(
            List<Customer> customers
    );

    List<CustomerWithProductsResponse> toWithProductsResponse(
            List<Customer> customers
    );
}