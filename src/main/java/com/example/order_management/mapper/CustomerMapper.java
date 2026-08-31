package com.example.order_management.mapper;

import com.example.order_management.dto.address.AddressResponse;
import com.example.order_management.dto.customer.AllCustomerDetailsResponse;
import com.example.order_management.dto.customer.CustomerDetailResponse;
import com.example.order_management.dto.order.OrderResponse;
import com.example.order_management.entity.Address;
import com.example.order_management.entity.Customer;
import com.example.order_management.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {

    public static CustomerDetailResponse toResponse(Customer customer){

        CustomerDetailResponse response = new CustomerDetailResponse();
        response.setId(customer.getId());
        response.setName(customer.getName());
        response.setEmail(customer.getEmail());

       List<OrderResponse> orders = customer.getOrders().stream().map(CustomerMapper::toResponse).toList();

        response.setOrders(orders);
        return response;
    }

    public static OrderResponse toResponse(Order order){

        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setOrderNumber(order.getOrderNumber());
        response.setAmount(order.getAmount());
        return response;
    }

    public static List<AllCustomerDetailsResponse> toAllCustomerDetailsMapper(List<Customer> customers){

        List<AllCustomerDetailsResponse> result = new ArrayList<>();

        for(Customer customer:customers){
            AllCustomerDetailsResponse allCustomerDetailResponse = new AllCustomerDetailsResponse();
            allCustomerDetailResponse.setId(customer.getId());
            allCustomerDetailResponse.setName(customer.getName());
            allCustomerDetailResponse.setEmail(customer.getEmail());

            Address address = customer.getAddress();

            // get only essential address details
            AddressResponse addressResponse = new AddressResponse();
            addressResponse.setStreet(address.getStreet());
            addressResponse.setCity(address.getCity());
            addressResponse.setState(address.getState());
            addressResponse.setCountry(address.getCountry());

            // add the result to user
            allCustomerDetailResponse.setAddress(addressResponse);

            result.add(allCustomerDetailResponse);

        }

       return result;
    }
}
