package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OneToManyBidirectional implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OneToManyBidirectional.class, args);
	}

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private OrdersRepository ordersRepository;

	@Override
	public void run(String... args) throws Exception {
		Orders o1 = new Orders("laptop", 4);
		Orders o2 = new Orders("mobile", 2);
		Customer customer = new Customer("John");
		o1.setCustomer(customer);
		o2.setCustomer(customer);
		List<Orders> orders = List.of(o1, o2);
		customer.setOrders(orders);
		customerRepository.save(customer);

		Customer c1 = new Customer("dm1");
		c1.setOrders(List.of());
		customerRepository.save(c1);

		// customerRepository.findAll().forEach(System.out::println);

		// System.out.println("-------------------------------------------");
		// ordersRepository.findAll().forEach(System.out::println);

		System.out.println("-------------------------------------------");
		// ordersRepository.findOrdersByCustomerName("John").forEach(System.out::println);
		// ordersRepository.customersWithNoOrders().forEach(System.out::println);
		// System.out.println(ordersRepository.fetchCustomerWithOrders(1L));

		ordersRepository.getCustomerWithQuantityGreater(1).forEach(System.out::println);
	}
}
