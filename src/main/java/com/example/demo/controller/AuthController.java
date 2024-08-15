package com.example.demo.controller;

import com.example.demo.*;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String registerUser(@RequestBody User user) {
        try {
            userService.registerUser(user.getUsername(), user.getEmail(), user.getPassword());
            return "User registered successfully!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/signin")
    public String loginUser(@RequestBody User user) {
        try {
            User loggedInUser = userService.loginUser(user.getUsername(), user.getPassword());
            return "User logged in successfully! User ID: " + loggedInUser.getId();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
