package com.example.order_management.repository;

import com.example.order_management.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer,Long> {

    //1. normal JPQL
    @Query("""
    SELECT c 
    FROM Customer c
""")
    List<Customer> findAllCustomers();


    // 2.inner join
    @Query("""
        SELECT DISTINCT c
        FROM Customer c
        JOIN c.orders o
""")
    List<Customer> findCustomersWithOrdersUsingJoin();


    // 3.INNER JOIN FETCH

    @Query("""
    SELECT DISTINCT c FROM Customer c
    JOIN FETCH c.orders
""")
    List<Customer> findCustomersWithOrders();


    //4.LEFT JOIN FETCH
    @Query("""
        SELECT DISTINCT c FROM Customer c
        LEFT JOIN FETCH c.orders
        """)
    List<Customer> findAllCustomersWithOrders();

    @Query("""
    SELECT DISTINCT c
    FROM Customer c
    JOIN c.orders o
    where o.amount > :amount
""")
    List<Customer> findCustomersWithLargeOrders(@Param("amount")BigDecimal amount);

}
