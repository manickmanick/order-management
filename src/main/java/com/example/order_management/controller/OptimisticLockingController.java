package com.example.order_management.controller;

import com.example.order_management.service.OptimisticLockingExperimentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/optimistic-lock")
public class OptimisticLockingController {

    private final OptimisticLockingExperimentService experimentService;

    public OptimisticLockingController(
            OptimisticLockingExperimentService experimentService) {

        this.experimentService = experimentService;
    }

    @PostMapping("/{orderId}")
    public String runExperiment(
            @PathVariable Long orderId)
            throws InterruptedException {

        experimentService.runExperiment(orderId);

        return "Optimistic locking experiment completed";
    }
}