package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class Pagination implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Pagination.class, args);
	}

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {
		customerRepository.save(new Customer("dm101"));
		customerRepository.save(new Customer("dm102"));
		customerRepository.save(new Customer("dm103"));
		customerRepository.save(new Customer("dm104"));
		customerRepository.save(new Customer("dm105"));
		customerRepository.save(new Customer("dm106"));

		// customerRepository.findAll().forEach(System.out::println);

		// customerRepository.findAll(Pageable.ofSize(4)).get().forEach(System.out::println);

		// Pageable pageable = PageRequest.of(1, 3);
		// customerRepository.findAll(pageable).getContent().forEach(System.out::println);

		// Pageable pageable = PageRequest.of(0, 3);
		// customerRepository.findByIdGreaterThan(2L,
		// pageable).getContent().forEach(System.out::println);

		// Pageable pageable = PageRequest.of(0, 3);
		// customerRepository.getAllCustomer(pageable).getContent().forEach(System.out::println);

		// native query
		// Pageable pageable = PageRequest.of(1, 3);
		// customerRepository.printAllCustomers(pageable).getContent().forEach(System.out::println);

		// sorting
		// Sort sort = Sort.by("name").ascending();
		// customerRepository.allCustomer(sort).forEach(System.out::println);

		// sorting in native query
		customerRepository.printAllCustomersSorting().forEach(System.out::println);

	}
}
