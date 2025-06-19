package com.example;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // @Query("SELECT c FROM Customer c WHERE c.address.city = ?1") //JPQL
    // public List<Customer> findByCity(String city);
    // public List<Customer> findByAddressCity(String city); // query by method name

    // public List<CustomerNameCity> findByAddress_City(String city);
    List<CustomerNameCity> findByAddress_City(String city); // projection
}
