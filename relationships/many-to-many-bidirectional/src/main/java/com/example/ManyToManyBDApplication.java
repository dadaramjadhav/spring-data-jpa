package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManyToManyBDApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ManyToManyBDApplication.class, args);
	}

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void run(String... args) throws Exception {
		Student s1 = new Student("sachin");
		Student s2 = new Student("rohit");

		Courses c1 = new Courses("java");
		Courses c2 = new Courses("python");
		Courses c3 = new Courses("react");
		Courses c4 = new Courses("angular");

		s1.setCourses(List.of(c1, c2));

		s2.setCourses(List.of(c1, c3, c4));

		studentRepository.saveAll(List.of(s1, s2));

		studentRepository.findAll().forEach(System.out::println);

		courseRepository.findAll().forEach(System.out::println);
	}
}
