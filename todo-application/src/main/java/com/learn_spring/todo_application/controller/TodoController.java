package com.learn_spring.todo_application.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.learn_spring.todo_application.model.Todo;
import com.learn_spring.todo_application.service.TodoService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@SessionAttributes("name")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(value = "list-todos", method = RequestMethod.GET)
    public String listTodos(ModelMap map) {
        var todos = this.todoService.getTodosByUser("username");
        map.put("todos", todos);
        return "listTodos";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String addTodo(ModelMap map) {
        var todo = new Todo(0, (String) map.get("name"), "", LocalDate.now().plusMonths(1), false);
        map.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String createNewTodo(ModelMap map, @Valid Todo todo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "todo";
        }
        this.todoService.createTodo((String) map.get("name"), todo.getDescription(), LocalDate.now().plusMonths(1),
                false);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "delete-todo")
    public String requestMethodName(@RequestParam int id) {
        this.todoService.deleteTodoById(id);
        return "redirect:list-todos";
    }

}
