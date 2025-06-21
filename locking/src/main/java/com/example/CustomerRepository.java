package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Customer save(Customer customer);
}
