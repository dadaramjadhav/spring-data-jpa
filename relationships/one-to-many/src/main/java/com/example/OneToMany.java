package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OneToMany implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OneToMany.class, args);
	}

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {
		List<Orders> orders = List.of(new Orders("laptop", 4), new Orders("mobile", 2));
		Customer customer = new Customer("John", orders);

		customerRepository.save(customer);

		customerRepository.findAll().forEach(System.out::println);

	}
}
