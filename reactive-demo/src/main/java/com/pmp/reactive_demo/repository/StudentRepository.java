package com.pmp.reactive_demo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.pmp.reactive_demo.model.Student;

import reactor.core.publisher.Flux;

public interface StudentRepository extends ReactiveCrudRepository<Student, Integer> {

    Flux<Student> findAllByFirstNameIgnoreCase(String firstName);
}
