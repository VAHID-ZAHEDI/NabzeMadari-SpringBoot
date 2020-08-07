package com.example.api.pregnancy.repositories;


import com.example.api.pregnancy.models.ERole;
import com.example.api.pregnancy.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
