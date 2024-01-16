package com.bdi.projectbdigroup5.controller;

import com.bdi.projectbdigroup5.model.User;
import com.bdi.projectbdigroup5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String hello(){
        return "Hello world!";
    }
    @GetMapping("/users")
    public Iterable<User> getAllUsers(){
        return userService.findAllUsers();
    }

}
