package com.pmp.reactive_demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.pmp.reactive_demo.model.Student;
import com.pmp.reactive_demo.service.StudentService;

@SpringBootApplication
public class ReactiveDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentService studentService) {
		return (x) -> {
			for (int i = 0; i < 100; i++) {
				studentService.save(
						Student
								.builder()
								.firstName("FN-" + i)
								.lastName("LN" + i)
								.age(i)
								.build())
						.subscribe();
			}
		};
	}
}
