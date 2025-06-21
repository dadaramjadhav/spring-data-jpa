package com.example;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    @Query("select o from Orders o join o.customer c where c.name=:name")
    public List<Orders> findOrdersByCustomerName(String name);

    // left join
    // to deal with lazy loading problem
    @Query("select new com.example.CustomerDTO(c.id, c.name) from Customer c left join c.orders o where o is null")
    public List<CustomerDTO> customersWithNoOrders();

    // JOIN FETCH pulls the associated orders in the same query — no lazy loading
    // later. avoid N+1 problem
    @Query("select c from Customer c join fetch  c.orders where c.id=:id")
    public Customer fetchCustomerWithOrders(@Param("id") Long id);


    //getting customers who placed order quantity greater than 5
    @Query("select c from Customer c join fetch c.orders o where o.quantity > ?1")
    public List<Customer> getCustomerWithQuantityGreater(int quantity);
}
