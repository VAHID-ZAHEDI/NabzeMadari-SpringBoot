package com.example.api.pregnancy.controllers;

import com.example.api.pregnancy.models.Header;
import com.example.api.pregnancy.models.User;
import com.example.api.pregnancy.repositories.HeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HeaderController {
    HeaderRepository headerRepository;

    @Autowired
    public HeaderController(HeaderRepository headerRepository) {
        this.headerRepository = headerRepository;
    }

    @PostMapping(value = "/addNewHeader")
    public @ResponseBody
    ResponseEntity<?> addNewHeader(@RequestBody Header header) {

        headerRepository.save(header);
        return ResponseEntity.ok("با موفقیت اضافه شد.");
    }

    @GetMapping(value = "/getAllHeaders")
    public @ResponseBody
    ResponseEntity<?> getAllHeader() {
        List<Header> header = headerRepository.findAll();
        return ResponseEntity.ok(header);
    }
}
