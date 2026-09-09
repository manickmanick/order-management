package com.example.order_management.service;

import com.example.order_management.entity.Order;
import com.example.order_management.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;

@Service
public class OptimisticLockingTransactionService {

    private final OrderRepository orderRepository;

    public OptimisticLockingTransactionService(
            OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void updateOrder(
            Long orderId,
            BigDecimal newAmount,
            CountDownLatch bothTransactionsRead) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        System.out.println(
                Thread.currentThread().getName()
                        + " → Read amount = "
                        + order.getAmount()
                        + ", version = "
                        + order.getVersion()
        );

        /*
         * Change the managed entity.
         * Hibernate will detect this change.
         */
        order.setAmount(newAmount);

        /*
         * Tell the experiment that this transaction
         * has finished reading the old version.
         */
        bothTransactionsRead.countDown();

        try {
            /*
             * Wait until both Transaction A and
             * Transaction B have read version = 0.
             */
            bothTransactionsRead.await();

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            throw new RuntimeException(e);
        }

        System.out.println(
                Thread.currentThread().getName()
                        + " → Trying to update amount = "
                        + newAmount
        );

        /*
         * Force Hibernate to execute the UPDATE now
         * instead of waiting until transaction commit.
         */
        orderRepository.flush();

        System.out.println(
                Thread.currentThread().getName()
                        + " → Update successful"
        );
    }
}