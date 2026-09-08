package com.example.order_management.controller;

import com.example.order_management.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction-demo")
public class TransactionDemoController {

    private final OrderService orderService;

    public TransactionDemoController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place-order/{customerId}")
    public String placeOrder(
            @PathVariable Long customerId,
            @RequestParam(defaultValue = "false") boolean failAfterAudit) {

        orderService.placeOrder(customerId, failAfterAudit);

        return "Order processing completed";
    }

}
