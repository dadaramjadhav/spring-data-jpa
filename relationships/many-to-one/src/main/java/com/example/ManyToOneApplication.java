package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManyToOneApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ManyToOneApplication.class, args);
	}

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrdersRepository ordersRepository;

	@Override
	public void run(String... args) throws Exception {
		Customer customer = new Customer();
		customer.setName("John Doe");
		customerRepository.save(customer);

		customerRepository.findAll().forEach(System.out::println);

		ordersRepository.save(new Orders("mobile", 2, customer));
		ordersRepository.save(new Orders("laptop", 1, customer));

		ordersRepository.findAll().forEach(System.out::println);
	}
}
