package com.learn_spring.todo_application.controller;

import java.time.LocalDate;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.learn_spring.todo_application.model.Todo;
import com.learn_spring.todo_application.repository.TodoRepository;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@SessionAttributes("name")
public class TodoController {

    private TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @RequestMapping(value = "list-todos", method = RequestMethod.GET)
    public String listTodos(ModelMap map) {
        var todos = this.todoRepository.findByUsername(getLoggedInUserName());
        map.put("todos", todos);
        return "listTodos";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String addTodo(ModelMap map) {
        var todo = new Todo(0, this.getLoggedInUserName(), "", LocalDate.now().plusMonths(1), false);
        map.put("todo", todo);

        return "todo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String createNewTodo(ModelMap map, @Valid Todo todo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "todo";
        }

        todo.setUserName(getLoggedInUserName());
        this.todoRepository.save(todo);

        return "redirect:list-todos";
    }

    @RequestMapping(value = "delete-todo")
    public String deleteTodo(@RequestParam int id) {
        this.todoRepository.deleteById(id);

        return "redirect:list-todos";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String getUpdateTodo(@RequestParam int id, ModelMap map) {
        var todo = this.todoRepository.findById(id).get();

        map.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    // The Binding result object should always be followed by the @Valid object,
    // else Spring won't be able to properly associate the binding result with the
    // model object, leading to errors.
    public String postUpdateTodo(@Valid Todo todo, BindingResult bindingResult, ModelMap map) {
        if (bindingResult.hasErrors()) {
            return "todo";
        }

        todo.setUserName(this.getLoggedInUserName());

        if (this.todoRepository.findById(todo.getId()).isPresent()) {
            this.todoRepository.deleteById(todo.getId());
        }
        this.todoRepository.save(todo);

        return "redirect:list-todos";
    }

    private String getLoggedInUserName() {
        var authenticationDetails = SecurityContextHolder.getContext().getAuthentication();
        return authenticationDetails.getName();
    }

}
