package com.example.api.pregnancy.controllers;

import com.example.api.pregnancy.models.ErrorModel;
import com.example.api.pregnancy.models.User;
import com.example.api.pregnancy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/addNewUser")
    public @ResponseBody
    ResponseEntity<?> insertUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok("ثبت نام با موفقیت انجام شد.");
    }

    @PostMapping(value = "/getUser")
    public @ResponseBody
    ResponseEntity<?> getUser(String phoneNumber) {
        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
        return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.badRequest().body(new ErrorModel("user not found"));
    }

    @GetMapping(value = "/getAllUser")
    public @ResponseBody
    ResponseEntity<?> getAllUser() {
        List<User> user = userRepository.findAll();
        return ResponseEntity.ok(user);
    }
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> userAccess(Authentication authentication, Principal principal) {
        Optional<User> userOptional = userRepository.findByPhoneNumber(principal.getName());
        return ResponseEntity.ok(userOptional.get());
    }
}
