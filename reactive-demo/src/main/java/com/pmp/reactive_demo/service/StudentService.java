package com.pmp.reactive_demo.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.pmp.reactive_demo.model.Student;
import com.pmp.reactive_demo.repository.StudentRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Flux<Student> findAll() {
        return this.studentRepository.findAll().delayElements(Duration.ofSeconds(1));
    }

    public Mono<Student> findById(int id) {
        return this.studentRepository.findById(id);
    }

    public Flux<Student> findByFirstNameIgnoreCase(String firstName) {
        return this.findByFirstNameIgnoreCase(firstName);
    }

    public Mono<Student> save(Student student) {
        return this.studentRepository.save(student);
    }

}
