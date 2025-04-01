package com.pmp.reactive_demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pmp.reactive_demo.model.Student;
import com.pmp.reactive_demo.service.StudentService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/vi/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping()
    public Mono<Student> save(@RequestBody Student student) {
        return this.studentService.save(student);
    }

    @GetMapping("/all")
    public Flux<Student> findAll() {
        return this.studentService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Student> findById(@PathVariable("id") Integer id) {
        return this.studentService.findById(id);
    }

    @GetMapping("/search")
    public Flux<Student> findByFirstName(@RequestParam(required = false) String firstName) {
        return this.studentService.findByFirstNameIgnoreCase(firstName);
    }

}
