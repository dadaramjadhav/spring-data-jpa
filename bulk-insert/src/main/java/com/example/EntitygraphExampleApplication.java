package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class EntitygraphExampleApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EntitygraphExampleApplication.class, args);
	}

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args) throws Exception {
		List<Customer> customers = List.of(new Customer("laptop"), new Customer("mobile"), new Customer("tv"));

		jdbcTemplate.batchUpdate("insert into customer (name) values (?)", customers, 50, (ps, customer) -> {
			ps.setString(1, customer.getName());
		});
		customerRepository.findAll().forEach(System.out::println);
	}
}
