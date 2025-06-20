package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManyToOneBiDirectionalApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ManyToOneBiDirectionalApplication.class, args);
	}

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrdersRepository ordersRepository;

	@Override
	public void run(String... args) throws Exception {
		Customer customer = new Customer();
		customer.setName("John Doe");

		Orders order1 = new Orders("Product A", 2, customer);
		Orders order2 = new Orders("Product B", 3, customer);

		List<Orders> orders = List.of(order1, order2);
		customer.setOrders(orders);

		customerRepository.save(customer);

		customerRepository.findAll().forEach(System.out::println);

		ordersRepository.findAll().forEach(System.out::println);
	}
}
