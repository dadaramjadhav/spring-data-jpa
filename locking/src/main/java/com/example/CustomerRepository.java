package com.example;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // // @Lock(LockModeType.PESSIMISTIC_WRITE)
    // public Customer save(Customer customer);

    // ===================================================================
    // pessimistic read example

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Optional<Customer> findById(Long id);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select c from Customer c where c.id=?1")
    public Customer readCustomerById(Long id);
}
