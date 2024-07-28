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
        todos.add(new Todo(1, "username", "learn spring", LocalDate.now().plusMonths(6), false));
        todos.add(new Todo(2, "username", "learn aws", LocalDate.now().plusMonths(6), false));
        todos.add(new Todo(3, "username", "learn kubernetes", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(4, "username", "learn DSA", LocalDate.now().plusMonths(3), false));
    }

    public List<Todo> getTodosByUser(String userName) {
        return todos;
    }

    public void createTodo(String username, String description, LocalDate localDate, boolean done) {
        todos.add(new Todo(todos.size() + 1, username, description, localDate, done));
    }

    public void deleteTodoById(int id) {
        todos.removeIf(todo -> todo.getId() == id);
    }

    public Todo getTodoById(int id) {
        return todos.stream().filter(todo -> todo.getId() == id).findFirst().get();
    }

    public void updateTodo(Todo todo) {
        deleteTodoById(todo.getId());
        todos.add(todo);
    }
}
