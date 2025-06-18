package com.example;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Cacheable(value = "customer", key = "#id")
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @CachePut(value = "customer", key = "#customer.id")
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @CacheEvict(value = "customer", key = "#id")
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @CacheEvict(value = "customer", allEntries = true)
    public void clearCustomerCache() {
        System.out.println("All customer cache cleared");
    }
}
