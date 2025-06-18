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

	@Autowired
	private MyOrdersRepository myOrdersRepository;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("--------------adding records----------------");
		Customer customer = new Customer();
		customer.setName("John Doe");
		List<MyOrders> orders = List.of(new MyOrders("laptop", customer), new MyOrders("mobile", customer));
		customer.setOrders(orders);

		customerRepository.save(customer);
		System.out.println("--------------fetching records----------------");
		customerRepository.findAll().forEach(System.out::println);

		System.out.println("--------------fetching orders----------------");
		myOrdersRepository.findAll().forEach(System.out::println);
	}
}
