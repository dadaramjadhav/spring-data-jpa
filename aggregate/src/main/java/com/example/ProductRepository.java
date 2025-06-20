package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select count(p) from Product p")
    public Long totalProducts();

    @Query("select min(p.price) from Product p")
    public int minPrice();

    @Query("select sum(p.quantity) from Product p")
    public int sumQuantity();

    @Query("select avg(p.price) from Product p")
    public double avgPrice();

    @Query("select max(p.quantity) from Product p")
    public int maxQuantity();
}
