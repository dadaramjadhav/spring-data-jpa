package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OneToOneBidirectional implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OneToOneBidirectional.class, args);
	}

	@Autowired
	UserRepository userRepository;
	@Autowired
	ProfileRepository profileRepository;

	@Override
	public void run(String... args) throws Exception {
		Profile p1 = new Profile("spring boot developer");
		Profile p2 = new Profile("react developer");
		Profile p3 = new Profile("angular developer");
		User u1 = new User("dm", p1);
		User u2 = new User("dm1", p2);
		User u3 = new User("dm", p3);
		p1.setUser(u1);
		p2.setUser(u2);
		p3.setUser(u3);

		userRepository.save(u1);
		userRepository.save(u2);
		userRepository.save(u3);

		userRepository.findAll().forEach(System.out::println);
		profileRepository.findAll().forEach(System.out::println);
		System.out.println("----------------------------------------");
		profileRepository.findByUserName("dm").forEach(System.out::println);
		System.out.println("----------------------------------------");

		userRepository.deleteById(2L);
		userRepository.findAll().forEach(System.out::println);

	}
}
