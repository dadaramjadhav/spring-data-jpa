package com.example;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // @EntityGraph(attributePaths = "orders")
    List<Customer> findAll(); // Will fetch orders eagerly
}
