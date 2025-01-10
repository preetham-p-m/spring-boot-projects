package com.learn_spring.todo_application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {

    private Logger logger;

    public WelcomeController() {
        this.logger = LoggerFactory.getLogger(getClass());
    }

    @GetMapping("/")
    public String loginGet(ModelMap model) {
        this.logger.debug("{} trying to login", getLoggedInUserName());
        model.put("name", getLoggedInUserName());
        return "welcome";
    }

    private String getLoggedInUserName() {
        var authenticationDetails = SecurityContextHolder.getContext().getAuthentication();
        return authenticationDetails.getName();
    }
}