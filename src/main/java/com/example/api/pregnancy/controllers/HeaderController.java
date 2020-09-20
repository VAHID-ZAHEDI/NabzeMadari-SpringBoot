package com.example.api.pregnancy.controllers;

import com.example.api.pregnancy.models.Header;
import com.example.api.pregnancy.models.User;
import com.example.api.pregnancy.repositories.HeaderRepository;
import com.example.api.pregnancy.storage.StorageService;
import com.mongodb.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class HeaderController {
    HeaderRepository headerRepository;
    StorageService storageService;

    @Autowired
    public HeaderController(HeaderRepository headerRepository, StorageService storageService) {
        this.headerRepository = headerRepository;
        this.storageService = storageService;
    }

    @PostMapping(value = "addNewHeader")
    public @ResponseBody
    ResponseEntity<?> addNewHeader(@ModelAttribute Header header, @Nullable @RequestPart("file") MultipartFile file) {
        if (file != null) {
            header.setImagePath(storageService.store(file));
//            storageService.store(file);
        }
        headerRepository.save(header);
        return ResponseEntity.ok("با موفقیت اضافه شد.");
    }

    @GetMapping(value = "getAllHeaders")
    public @ResponseBody
    ResponseEntity<?> getAllHeader() {
        List<Header> header = headerRepository.findAll();
        return ResponseEntity.ok(header);
    }
}
