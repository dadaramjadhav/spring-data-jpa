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

		customerRepository.findAll().forEach(System.out::println);
		cs.update();
	}
}
