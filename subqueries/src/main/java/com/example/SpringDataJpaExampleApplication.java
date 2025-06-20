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
		prodRepo.save(new Product("cable", 345, 6));
		prodRepo.save(new Product("monitor", 15000, 6));
		prodRepo.save(new Product("keyboard", 2500, 23));
		prodRepo.save(new Product("computer", 45000, 12));

		prodRepo.productPriceGreaterThanAvgPrice().forEach(System.out::println);

	}
}
