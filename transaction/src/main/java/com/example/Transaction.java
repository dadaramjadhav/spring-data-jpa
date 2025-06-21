package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Transaction implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Transaction.class, args);
	}

	@Autowired
	AccountRepository ar;

	@Autowired
	OuterService os;

	@Override
	public void run(String... args) throws Exception {

		// // REQUIRED ==>
		// try {
		// os.outerMethod();
		// } catch (RuntimeException re) {
		// System.out.println(re.getMessage());
		// }

		// // REQUIRES_NEW ==>
		try {
			os.outerMethod();
		} catch (MyException re) {
			System.out.println(re.getMessage());
		}
	}
}
