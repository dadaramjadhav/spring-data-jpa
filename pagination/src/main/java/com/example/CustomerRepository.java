package com.example;

import java.util.List;
 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Page<Customer> findByIdGreaterThan(Long id, Pageable pageable);

    @Query("select c from Customer c")
    public Page<Customer> getAllCustomer(Pageable pageable);

    // native query
    @Query(value = "select * from customer", nativeQuery = true)
    public Page<Customer> printAllCustomers(Pageable pageable);

    //sorting
    @Query("select c from Customer c")
    public List<Customer> allCustomer(Sort sort);

        // native query ===> not supported, needs to add in query itself
    @Query(value = "select * from customer order by name asc", nativeQuery = true)
    public List<Customer> printAllCustomersSorting();

}
