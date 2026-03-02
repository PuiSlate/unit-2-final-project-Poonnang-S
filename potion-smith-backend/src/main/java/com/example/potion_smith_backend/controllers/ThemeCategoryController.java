package com.example.potion_smith_backend.controllers;

import com.example.potion_smith_backend.models.ThemeCategory;
import com.example.potion_smith_backend.repositories.ThemeCategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//Make it read-only for now, we can add create/update/delete functionality later if we want to

@RestController
@RequestMapping("/api/theme-categories")
public class ThemeCategoryController {

    private final ThemeCategoryRepository repository;

    public ThemeCategoryController(ThemeCategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<ThemeCategory> getAll() {
        return repository.findAll();
    }
}


