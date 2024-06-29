package com.learn_spring.jpa_and_hibernate_v1.course.jdbc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.learn_spring.jpa_and_hibernate_v1.course.Course;

@Component
public class CourseJdbcCommandLineRunner implements CommandLineRunner {

    private CourseJdbcRepository repository;

    public CourseJdbcCommandLineRunner(CourseJdbcRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        repository.insert(new Course(1, "Learn AWS 1", "in28minutes"));

        System.out.println(repository.findById(1));
    }

}
