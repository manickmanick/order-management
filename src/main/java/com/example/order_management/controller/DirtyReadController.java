package com.example.order_management.controller;


import com.example.order_management.service.DirtyReadReaderService;
import com.example.order_management.service.DirtyReadWriterService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dirty-read")
public class DirtyReadController {

    private final DirtyReadWriterService writerService;
    private final DirtyReadReaderService readerService;

    public DirtyReadController(
            DirtyReadWriterService writerService,
            DirtyReadReaderService readerService) {

        this.writerService = writerService;
        this.readerService = readerService;
    }

    @PostMapping("/update/{orderId}")
    public String update(@PathVariable Long orderId){
        writerService.updateOrderAndWait(orderId);
        return "update completed.";
    }

    @GetMapping("/read/{orderId}")
    public String read(@PathVariable Long orderId){
        return readerService.readOrderAmount(orderId).toString();
    }

}
