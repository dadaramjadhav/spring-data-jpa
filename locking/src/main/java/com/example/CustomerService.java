package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository cs;

    @Transactional
    public void update() {
        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " acquiring lock...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            cs.save(new Customer(3, "new dm1234"));
        });
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " acquiring lock...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            cs.save(new Customer(3, "new dm"));
        });

        t1.start();
        t2.start();

    }
}
