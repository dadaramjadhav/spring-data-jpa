package com.example;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EntitygraphExampleApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EntitygraphExampleApplication.class, args);
	}

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {
		customerRepository.save(new Customer("laptop", 31000, 4));
		customerRepository.save(new Customer("mobile", 32000, 5));

		customerRepository.findAll().forEach(System.out::println);

		Optional<Customer> optionalCustomer = customerRepository.findById(1L);
		Customer cust = optionalCustomer.get();
		cust.setName("new laptop");
		cust.setPrice(123);
		customerRepository.save(cust);
	}
}
