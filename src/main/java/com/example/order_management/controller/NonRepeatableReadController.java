package com.example.order_management.controller;

import com.example.order_management.service.NonRepeatableReadService;
import com.example.order_management.service.NonRepeatableReadWriterService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/non-repeatable-read")
public class NonRepeatableReadController {

    private final NonRepeatableReadService readerService;
    private final NonRepeatableReadWriterService writerService;

    public NonRepeatableReadController(
            NonRepeatableReadService readerService,
            NonRepeatableReadWriterService writerService) {

        this.readerService = readerService;
        this.writerService = writerService;
    }

    @GetMapping("/read/{orderId}")
    public String read(@PathVariable Long orderId) {

        readerService.readOrderTwice(orderId);

        return "Read completed";
    }

    @PutMapping("/update/{orderId}")
    public String update(@PathVariable Long orderId) {

        writerService.updateOrder(orderId);

        return "Update completed";
    }

}
