package com.example.api.pregnancy.repositories;

import com.example.api.pregnancy.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findAllByPhoneNumber(String phoneNumber);

    List<User> findAll();
}
