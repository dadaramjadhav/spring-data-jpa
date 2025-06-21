package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Migration implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Migration.class, args);
	}

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {
		customerRepository.save(new Customer(1, "dm101"));
		customerRepository.save(new Customer(2, "dm102"));
		customerRepository.save(new Customer(3, "dm103"));
		customerRepository.save(new Customer("dm101"));
		customerRepository.save(new Customer("dm102"));
		customerRepository.save(new Customer("dm103"));

		customerRepository.findAll().forEach(System.out::println);

	}
}
