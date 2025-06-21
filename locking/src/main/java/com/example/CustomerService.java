package com.example;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository cr;

    // pessimistic write example
    @Transactional
    public void update() {
        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " acquiring lock...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            cr.save(new Customer(3L, "new dm1234"));
        });
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " acquiring lock...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            cr.save(new Customer(3L, "new dm"));
        });

        t1.start();
        t2.start();
    }

    // ===================================================================
    // pessimistic read example
    @Transactional
    public Customer getCustomerById(Long id) {
        System.out.println("getting pessimistic read lock " + new Date());
        Customer cust = cr.readCustomerById(id);
        System.out.println("lock acquired, reading customer: " + cust + "         " + new Date());
        try {
            Thread.sleep(10000);

        } catch (InterruptedException ie) {
        }
        System.out.println("finished reading " + new Date());
        return cust;
    }

    @Transactional
    public void updateCustomer(Long id) {
        System.out.println("attempting to update customer " + new Date());
        Customer cust = cr.findById(id).orElseThrow();
        cust.setName("new name" + new Date());
        cr.save(cust);
        System.out.println("account updated " + new Date());
    }
}
