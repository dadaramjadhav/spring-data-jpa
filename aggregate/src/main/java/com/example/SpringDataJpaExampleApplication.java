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
	private ProductRepository prodRepo;

	@Override
	public void run(String... args) throws Exception {
		prodRepo.save(new Product("laptop", 71100, 4));
		prodRepo.save(new Product("mobile", 45000, 2));

		prodRepo.findAll().forEach(System.out::println);
		System.out.println("total product: " + prodRepo.totalProducts());

		System.out.println("min price: " + prodRepo.minPrice());

		System.out.println("total quantity: " + prodRepo.sumQuantity());

		System.out.println("avg price: " + prodRepo.avgPrice());

		System.out.println("max quantity: " + prodRepo.maxQuantity());
	}
}
