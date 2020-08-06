package com.example.api.pregnancy.repositories;

import com.example.api.pregnancy.models.Header;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeaderRepository extends CrudRepository<Header, String> {
    List<Header> findAll();
}
