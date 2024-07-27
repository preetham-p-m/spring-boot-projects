package com.learn_spring.todo_application.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.learn_spring.todo_application.model.Todo;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<Todo>();

    static {
        todos.add(new Todo(0, "username", "learn spring", LocalDate.now().plusMonths(6), false));
        todos.add(new Todo(0, "username", "learn aws", LocalDate.now().plusMonths(6), false));
        todos.add(new Todo(0, "username", "learn kubernetes", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(0, "username", "learn DSA", LocalDate.now().plusMonths(3), false));
    }

    public List<Todo> getTodosByUser(String userName) {
        return todos;
    }
}
