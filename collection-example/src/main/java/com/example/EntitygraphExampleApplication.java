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
	private ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {
		// System.out.println("--------------adding records----------------");
		// Customer customer = new Customer();
		// customer.setName("John Doe");
		// List<String> products = List.of("laptop", "mobile");
		// customer.setProducts(products);

		// customerRepository.save(customer);
		// System.out.println("--------------fetching records----------------");
		// customerRepository.findAll().forEach(System.out::println);

		List<Review> reviews = List.of(new Review(1, "good"), new Review(2, "bad"));
		Product product = new Product();
		product.setProductName("laptop");
		product.setReviews(reviews);
		productRepository.save(product);

		productRepository.findAll().forEach(System.out::println);
	}
}
