package com.example.potion_smith_backend.controllers;

import com.example.potion_smith_backend.dtos.DrinkDTO;
import com.example.potion_smith_backend.dtos.response.DrinkResponseDTO;
import com.example.potion_smith_backend.models.Drink;
import com.example.potion_smith_backend.models.SpiritCategory;
import com.example.potion_smith_backend.models.ThemeCategory;
import com.example.potion_smith_backend.repositories.DrinkRepository;
import com.example.potion_smith_backend.repositories.SpiritCategoryRepository;
import com.example.potion_smith_backend.repositories.ThemeCategoryRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api/drinks")
public class DrinkController {

    private final DrinkRepository drinkRepository;
    private final SpiritCategoryRepository spiritCategoryRepository;
    private final ThemeCategoryRepository themeCategoryRepository;

    public DrinkController(
            DrinkRepository drinkRepository,
            SpiritCategoryRepository spiritCategoryRepository,
            ThemeCategoryRepository themeCategoryRepository
    ) {
        this.drinkRepository = drinkRepository;
        this.spiritCategoryRepository = spiritCategoryRepository;
        this.themeCategoryRepository = themeCategoryRepository;
    }

    // GET all drinks (with titles)
    @GetMapping("")
    public ResponseEntity<List<DrinkResponseDTO>> getAllDrinks() {
        List<DrinkResponseDTO> drinks = drinkRepository.findAll()
                .stream()
                .map(DrinkResponseDTO::new)
                .toList();
        return ResponseEntity.ok(drinks);
    }

    // GET drink by ID (with titles)
    @GetMapping("/details/{drinkId}")
    public ResponseEntity<DrinkResponseDTO> getDrinkById(@PathVariable int drinkId) {
        return drinkRepository.findById(drinkId)
                .map(drink -> ResponseEntity.ok(new DrinkResponseDTO(drink)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST new drink (accepts IDs)
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DrinkResponseDTO> addNewDrink(@Valid @RequestBody DrinkDTO drinkData) {
        Drink drink = new Drink(
                drinkData.getDrinkName(),
                drinkData.getDrinkInstructions(),
                drinkData.getDrinkIngredients(),
                drinkData.getImageId(),
                drinkData.isOnWeeklyFeature()
        );

        // Assign categories if IDs provided
        if (drinkData.getSpiritCategory() != null) {
            SpiritCategory spirit = spiritCategoryRepository.findById(drinkData.getSpiritCategory())
                    .orElse(null);
            drink.setSpiritCategory(spirit);
        }

        if (drinkData.getThemeCategory() != null) {
            ThemeCategory theme = themeCategoryRepository.findById(drinkData.getThemeCategory())
                    .orElse(null);
            drink.setThemeCategory(theme);
        }

        drinkRepository.save(drink);
        return new ResponseEntity<>(new DrinkResponseDTO(drink), HttpStatus.CREATED);
    }

    // DELETE drink
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrink(@PathVariable("id") int drinkId) {
        return drinkRepository.findById(drinkId)
                .map(drink -> {
                    drinkRepository.delete(drink);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // PUT / update drink (accepts IDs)
    @PutMapping("/{id}")
    public ResponseEntity<DrinkResponseDTO> updateDrink(@PathVariable int id, @RequestBody DrinkDTO updatedDrinkDTO) {
        return drinkRepository.findById(id).map(drink -> {

            drink.setDrinkName(updatedDrinkDTO.getDrinkName());
            drink.setDrinkIngredients(updatedDrinkDTO.getDrinkIngredients());
            drink.setDrinkInstructions(updatedDrinkDTO.getDrinkInstructions());
            drink.setImageId(updatedDrinkDTO.getImageId());
            drink.setOnWeeklyFeature(updatedDrinkDTO.isOnWeeklyFeature());

            // update SpiritCategory
            if (updatedDrinkDTO.getSpiritCategory() != null) {
                SpiritCategory spirit = spiritCategoryRepository.findById(updatedDrinkDTO.getSpiritCategory())
                        .orElse(null);
                drink.setSpiritCategory(spirit);
            } else {
                drink.setSpiritCategory(null);
            }

            // update ThemeCategory
            if (updatedDrinkDTO.getThemeCategory() != null) {
                ThemeCategory theme = themeCategoryRepository.findById(updatedDrinkDTO.getThemeCategory())
                        .orElse(null);
                drink.setThemeCategory(theme);
            } else {
                drink.setThemeCategory(null);
            }

            drinkRepository.save(drink);
            return ResponseEntity.ok(new DrinkResponseDTO(drink));

        }).orElse(ResponseEntity.notFound().build());
    }
}