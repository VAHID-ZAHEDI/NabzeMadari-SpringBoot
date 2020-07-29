package com.example.api.pregnancy.controllers;

import com.example.api.pregnancy.models.ErrorModel;
import com.example.api.pregnancy.models.User;
import com.example.api.pregnancy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/User")
    public @ResponseBody
    ResponseEntity<?> insertUser(@ModelAttribute User user) {
        userRepository.save(user);
        return ResponseEntity.ok("user successfully added");
    }

    @PostMapping(value = "/getUser")
    public @ResponseBody
    ResponseEntity<?> getUser(String phoneNumber) {
        Optional<User> user = userRepository.findAllByPhoneNumber(phoneNumber);
        return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.badRequest().body(new ErrorModel("user not found"));
    }

    @PostMapping(value = "/getAllUser")
    public @ResponseBody
    List<User> getAllUser() {
        List<User> user = userRepository.findAll();
        return user;
    }
}
