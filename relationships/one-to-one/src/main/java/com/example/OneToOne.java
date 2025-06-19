package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OneToOne implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OneToOne.class, args);
	}

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {
		Address a1 = new Address("pune1", "411046");
		Address a2 = new Address("pune2", "411047");

		customerRepository.save(new Customer("dm1", a1));
		customerRepository.save(new Customer("dm5", a2));
		customerRepository.save(new Customer("dm2", new Address("pune2", "411047")));
		customerRepository.save(new Customer("dm3", new Address("pune3", "411048")));

		// customerRepository.findByCity("pune1").forEach(System.out::println);
		// customerRepository.findByAddressCity("pune1").forEach(System.out::println);
		List<CustomerNameCity> list = customerRepository.findByAddress_City("pune1");
		for (CustomerNameCity c : list) {
			System.out.println(c.getName() + " - " + c.getAddress().getCity());
		}
		// delete
		customerRepository.deleteById(2);
	}
}
