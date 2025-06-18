package com.example;

import java.util.List;

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
		customerRepository.save(new Customer("laptop"));
		customerRepository.save(new Customer("mobile"));

		customerRepository.findAll().forEach(System.out::println);
	}
}
