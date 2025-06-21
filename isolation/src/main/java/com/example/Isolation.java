package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Isolation implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Isolation.class, args);
	}

	@Autowired
	AccountRepository ar;

	@Autowired
	AccountService as;

	@Override
	public void run(String... args) throws Exception {
		ar.save(new Account("dm101", 100));
		ar.save(new Account("dm102", 200));
		ar.save(new Account("dm103", 300));
		ar.save(new Account("dm104", 400));

		ExecutorService executor = Executors.newFixedThreadPool(2);
		// serializable
		executor.submit(() -> {
			as.readAccounts();
		});
		Thread.sleep(1000);

		executor.submit(() -> {
			as.addAccount();
		});
		executor.shutdown();

		// repeatable read
		// executor.submit(() -> {
		// as.readTwice();
		// });
		// Thread.sleep(2000);

		// executor.submit(() -> {
		// as.updateBalance();
		// });
		// executor.shutdown();

	}
}
