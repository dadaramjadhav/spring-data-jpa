package com.example;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @EntityGraph(attributePaths = "products")
    List<Customer> findAll(); // Will fetch orders eagerly
}
