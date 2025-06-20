package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManyToManyApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ManyToManyApplication.class, args);
	}

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void run(String... args) throws Exception {
		Student s1 = new Student("sachin");

		Courses c1 = new Courses("java");
		Courses c2 = new Courses("python");

		s1.setCourses(List.of(c1, c2));
		studentRepository.save(s1);

		studentRepository.findAll().forEach(System.out::println);

		courseRepository.findAll().forEach(System.out::println);
	}
}
