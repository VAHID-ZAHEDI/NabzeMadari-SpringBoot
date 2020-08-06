package com.example.api.pregnancy.repositories;

import org.springframework.stereotype.Service;

@Service
public class PurchaseDeptService {
    UserRepository userRepository;

    public PurchaseDeptService(UserRepository repository) {
        this.userRepository = repository;
    }
}
