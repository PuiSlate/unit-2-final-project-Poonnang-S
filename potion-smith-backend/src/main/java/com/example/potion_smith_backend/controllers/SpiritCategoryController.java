package com.example.potion_smith_backend.controllers;

import com.example.potion_smith_backend.repositories.SpiritCategoryRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Make it read-only for now, we can add create/update/delete functionality later if we want to


@RestController
@RequestMapping("/api/spirit-categories")
public class SpiritCategoryController {
    private final SpiritCategoryRepository repository;

    public SpiritCategoryController(SpiritCategoryRepository repository) {
        this.repository = repository;
     }

     @RequestMapping("")
     public Object getAll() {
         return repository.findAll();
     }
}
