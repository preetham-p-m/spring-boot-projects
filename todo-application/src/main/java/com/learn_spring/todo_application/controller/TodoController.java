package com.learn_spring.todo_application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.learn_spring.todo_application.service.TodoService;

@Controller
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(value = "list-todos", method = RequestMethod.GET)
    public String getTodos(ModelMap map) {
        var todos = this.todoService.getTodosByUser("username");
        map.put("todos", todos);
        return "listTodos";
    }
}
