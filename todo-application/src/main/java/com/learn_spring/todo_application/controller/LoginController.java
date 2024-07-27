package com.learn_spring.todo_application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.learn_spring.todo_application.model.User;
import com.learn_spring.todo_application.service.AuthenticationService;

@Controller
public class LoginController {

    private AuthenticationService authenticationService;

    private Logger logger;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
        this.logger = LoggerFactory.getLogger(getClass());
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginGet() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loginPost(@ModelAttribute("login") User user, ModelMap model) {

        if (authenticationService.isValidUser(user.getUserName(), user.getPassword())) {
            model.put("name", user.getUserName());
            return "welcome";
        }

        this.logger.error("Login failed for user: {}", user.getUserName());

        model.put("error", "Invalid Credentials. Please try again!");
        return "login";
    }
}