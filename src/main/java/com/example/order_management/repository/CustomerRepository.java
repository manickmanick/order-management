package com.example.order_management.repository;

import com.example.order_management.entity.Customer;
import com.example.order_management.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
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

//    @EntityGraph(attributePaths = "orders")
//    @Override
//    List<Customer> findAll();

//    Customer findByEmail(String email);
//
//    Customer findByNameAndEmail();
//
//    List<Customer> findByNameOrEmail(String name,String email);
//
//    List<Customer> findByNameContaining(String name);
//
//    List<Order> findByNameOrderByEmailAsc(String name);
//
//    Customer findFirstByName(String name);
//
//    List<Customer> findTop5ByOrderedByName();
//
//    List<Customer> findByAddress_city(String city);

    @Override
    Page<Customer> findAll(Pageable page);

    @Query("""
        SELECT DISTINCT c
        FROM Customer c
        JOIN FETCH c.orders
        """)
    Page<Customer> findCustomersWithOrders(Pageable pageable);

}
