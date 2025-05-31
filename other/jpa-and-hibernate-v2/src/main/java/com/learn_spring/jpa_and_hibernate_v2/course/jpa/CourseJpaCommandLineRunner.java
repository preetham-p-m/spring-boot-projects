package com.learn_spring.jpa_and_hibernate_v2.course.jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.learn_spring.jpa_and_hibernate_v2.course.Course;

@Component
public class CourseJpaCommandLineRunner implements CommandLineRunner {

    private CourseJapRepository repository;

    public CourseJpaCommandLineRunner(CourseJapRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        repository.insert(new Course(1, "Learn JPA 1", "in28minutes"));

        System.out.println(repository.findById(1));
    }

}
