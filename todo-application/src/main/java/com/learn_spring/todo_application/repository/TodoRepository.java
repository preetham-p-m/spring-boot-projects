package com.learn_spring.todo_application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learn_spring.todo_application.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    @Query("SELECT t FROM Todo t WHERE t.userName LIKE %:userName%")
    public List<Todo> findByUsername(String userName);
}
