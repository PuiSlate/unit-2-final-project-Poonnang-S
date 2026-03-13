package com.example.potion_smith_backend.controllers;

import com.example.potion_smith_backend.models.SpiritCategory;
import com.example.potion_smith_backend.repositories.SpiritCategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

//Make it read-only for now, we can add create/update/delete functionality later if we want to


@RestController
@RequestMapping("/api/spirit-categories")
public class SpiritCategoryController {
    private final SpiritCategoryRepository repository;

    public SpiritCategoryController(SpiritCategoryRepository repository) {
        this.repository = repository;
     }

//     This now gives frontend a clean list of titles. Frontend dropdown can fetch this instead of computing from drinks
    @GetMapping("")
    public List<String> getAllTitles() {
        return repository.findAll()
                .stream()
                .map(SpiritCategory::getTitle)
                .collect(Collectors.toList());
     }
}
