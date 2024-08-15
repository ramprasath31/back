package com.example.demo.service;

import com.example.demo.*;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User registerUser(String username, String email, String password) throws Exception {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new Exception("Username already exists.");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new Exception("Email already exists.");
        }

        User user = new User(username, email, password);
        return userRepository.save(user);
    }

    @Transactional
    public User loginUser(String username, String password) throws Exception {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found."));

        if (!user.getPassword().equals(password)) {
            throw new Exception("Invalid credentials.");
        }

        return user;
    }
}

