package com.example.order_management.controller;


import com.example.order_management.dto.product.ProductResponse;
import com.example.order_management.entity.Customer;
import com.example.order_management.entity.Product;
import com.example.order_management.mapper.ProductMapper;
import com.example.order_management.repository.CustomerRepository;
import com.example.order_management.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final ProductMapper productMapper;

    public ProductController(ProductRepository productRepository,CustomerRepository customerRepository, ProductMapper productMapper) {

        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.productMapper = productMapper;
    }


    @PostMapping
    public Product createProduct(@RequestBody Product product) {

        return productRepository.save(product);
    }

    @PostMapping("/{productId}/customers/{customerId}")
    public ProductResponse addCustomerToProduct(@PathVariable Long productId, @PathVariable Long customerId){
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new RuntimeException("Product was not found"));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new RuntimeException("Customer was not found"));

        customer.getProducts().add(product);

         productRepository.save(product);

         return productMapper.toResponse(product);

    }

}
