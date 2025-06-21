package com.example;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // inner join
    // @Query("select o from Orders o join o.customer c where c.name=:name")
    // public List<Orders> findOrdersByCustomerName(String name);
}
