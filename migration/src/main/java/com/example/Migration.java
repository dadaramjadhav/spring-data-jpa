package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Migration implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Migration.class, args);
	}

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {
		// customerRepository.save(new Customer(1, "dm101"));
		// customerRepository.save(new Customer(2, "dm102"));
		// customerRepository.save(new Customer(3, "dm103"));
		// customerRepository.save(new Customer("dm101"));
		// customerRepository.save(new Customer("dm102"));
		// customerRepository.save(new Customer("dm103"));

		// customerRepository.save(new Customer("dm1", "dm1@gmail.com"));
		// customerRepository.save(new Customer("dm2", "dm2@gmail.com"));
		// customerRepository.save(new Customer("dm3", "dm3@gmail.com"));
		// customerRepository.save(new Customer("dm4", "dm1@gmail.com", "9001"));
		// customerRepository.save(new Customer("dm5", "dm2@gmail.com", "9444"));

		// customerRepository.save(new Customer("dm4", "dm1@gmail.com", "9001",
		// "pune"));
		// customerRepository.save(new Customer("dm5", "dm2@gmail.com", "9444",
		// "mumbai"));

		customerRepository.save(new Customer("dm6", "9001", "pune"));
		customerRepository.save(new Customer("dm7", "9444", "mumbai"));

		customerRepository.findAll().forEach(System.out::println);

	}
}
