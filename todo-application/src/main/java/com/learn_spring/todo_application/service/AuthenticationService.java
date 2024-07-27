package com.learn_spring.todo_application.service;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean isValidUser(String userName, String password) {
        var isValidUserName = userName.equalsIgnoreCase("username");
        var isValidPassword = password.equalsIgnoreCase("password");
        return isValidUserName && isValidPassword;
    }

}
