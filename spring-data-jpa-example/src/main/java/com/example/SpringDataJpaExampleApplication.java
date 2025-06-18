package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataJpaExampleApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaExampleApplication.class, args);
	}

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception {
		// customerRepository.save(new Customer(1, "dm101"));
		// customerRepository.save(new Customer(2, "dm102"));
		// customerRepository.save(new Customer(3, "dm103"));
		// customerRepository.save(new Customer("dm101"));
		// customerRepository.save(new Customer("dm102"));
		// customerRepository.save(new Customer("dm103"));

		orderRepository.save(new MyOrders(new OrderId(101, 501), "order1", 590));
	}
}
