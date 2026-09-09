package com.example.order_management.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;

@Service
public class OptimisticLockingExperimentService {

    private final OptimisticLockingTransactionService transactionService;

    public OptimisticLockingExperimentService(
            OptimisticLockingTransactionService transactionService) {

        this.transactionService = transactionService;
    }

    public void runExperiment(Long orderId)
            throws InterruptedException {

        /*
         * CountDownLatch with count 2 means:
         *
         * Transaction A must reach countDown()
         * Transaction B must reach countDown()
         *
         * Only then can both continue.
         */
        CountDownLatch bothTransactionsRead =
                new CountDownLatch(2);


        Thread transactionA = new Thread(() -> {

            try {

                transactionService.updateOrder(
                        orderId,
                        new BigDecimal("4000.00"),
                        bothTransactionsRead
                );

            } catch (Exception e) {

                System.out.println(
                        "Transaction A failed: "
                                + e.getClass().getSimpleName()
                                + " - "
                                + e.getMessage()
                );
            }

        }, "Transaction-A");


        Thread transactionB = new Thread(() -> {

            try {

                transactionService.updateOrder(
                        orderId,
                        new BigDecimal("5000.00"),
                        bothTransactionsRead
                );

            } catch (Exception e) {

                System.out.println(
                        "Transaction B failed: "
                                + e.getClass().getSimpleName()
                                + " - "
                                + e.getMessage()
                );
            }

        }, "Transaction-B");


        transactionA.start();
        transactionB.start();


        /*
         * Wait for both threads to finish.
         */
        transactionA.join();
        transactionB.join();
    }
}