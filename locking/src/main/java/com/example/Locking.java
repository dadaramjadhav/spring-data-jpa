package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Locking implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Locking.class, args);
	}

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerService cs;

	@Override
	public void run(String... args) throws Exception {
		customerRepository.save(new Customer("dm101"));
		customerRepository.save(new Customer("dm102"));
		customerRepository.save(new Customer("dm103"));
		customerRepository.save(new Customer("dm101"));
		customerRepository.save(new Customer("dm102"));
		customerRepository.save(new Customer("dm103"));

		// pessimistic write
		// customerRepository.findAll().forEach(System.out::println);
		// cs.update();

		// pessimistic write
		Thread t1 = new Thread(() -> cs.getCustomerById(2L));
		Thread t2 = new Thread(() -> {
			try {
				Thread.sleep(2000);

			} catch (InterruptedException ie) {
			}
			cs.updateCustomer(2L);
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}
}
