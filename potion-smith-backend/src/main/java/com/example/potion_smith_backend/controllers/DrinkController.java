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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/") // Allow CORS requests from the React frontend running on port 5173
@RestController
@RequestMapping("/api/drinks")
public class DrinkController {

    @Autowired
    DrinkRepository drinkRepository;
    @Autowired
    SpiritCategoryRepository spiritCategoryRepository;
    @Autowired
    ThemeCategoryRepository themeCategoryRepository;

    // Constructor injection
    public DrinkController(
            DrinkRepository drinkRepository,
            SpiritCategoryRepository spiritCategoryRepository,
            ThemeCategoryRepository themeCategoryRepository
    ) {
        this.drinkRepository = drinkRepository;
        this.spiritCategoryRepository = spiritCategoryRepository;
        this.themeCategoryRepository = themeCategoryRepository;
    }


    //    Retrieve all drinks from database
    // refactored to return a ResponseEntity object with an HttpStatus of 200 OK
//    GET http://localhost:8080/api/drinks
    @GetMapping("")
    public ResponseEntity<List<DrinkResponseDTO>> getAllDrinks() {
        List<DrinkResponseDTO> drinks = drinkRepository.findAll()
                .stream()
                .map(DrinkResponseDTO::new)
                .toList();
        return ResponseEntity.ok(drinks);
    }

    //    Retrieve a specific drink object using its id
//    refactored to return a ResponseEntity object with an HttpStatus of 200 OK
//    GET http://localhost:8080/api/drinks/details/3 (for example)
    @GetMapping("/details/{drinkId}")
    public ResponseEntity<DrinkResponseDTO> getDrinkById(@PathVariable int drinkId) {
        return drinkRepository.findById(drinkId)
                .map(drink -> ResponseEntity.ok(new DrinkResponseDTO(drink)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    //     Save a new drink to the database
//    refactored from RequestParams to a RESTful controller by using @RequestBody to accept JSON
//    for POST requests, persisted data using Spring Data JPA, and returned proper HTTP status codes using ResponseEntity.
//    POST http://localhost:8080/api/drinks/add
//Ensure the mapping is configured to consume JSON instead of using query params
    @PostMapping(value= "/add", consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewDrink(@Valid @RequestBody DrinkDTO drinkData) {
        Drink drink = new Drink(drinkData.getDrinkName(), drinkData.getDrinkInstructions(), drinkData.getDrinkIngredients(), drinkData.getImageId(), drinkData.isOnWeeklyFeature());
        drinkRepository.save(drink);
        return new ResponseEntity<>(drink, HttpStatus.CREATED); //201

    }


    //    Delete a drink from the database
//    DELETE http://localhost:8080/api/drinks/3 (for example)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrink(@PathVariable int drinkId) {
        return drinkRepository.findById(drinkId)
                .map(drink -> {
                    drinkRepository.delete(drink);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

//    Put endpoint to UPDATE an existing drink in the database. Frontend JSON request (no nested objects, just titles)
//    PUT http://localhost:8080/api/drinks/update/3 (for example)
@PutMapping("/{id}")
public ResponseEntity<DrinkResponseDTO> updateDrink(@PathVariable int id, @RequestBody DrinkDTO updatedDrinkDTO) {

    return drinkRepository.findById(id).map(drink -> {

        // --- update basic fields ---
        drink.setDrinkName(updatedDrinkDTO.getDrinkName());
        drink.setDrinkIngredients(updatedDrinkDTO.getDrinkIngredients());
        drink.setDrinkInstructions(updatedDrinkDTO.getDrinkInstructions());
        drink.setImageId(updatedDrinkDTO.getImageId());
        drink.setOnWeeklyFeature(updatedDrinkDTO.isOnWeeklyFeature());

        // --- fetch managed SpiritCategory from DB ---
        if (updatedDrinkDTO.getSpiritCategory() != null) {
            SpiritCategory spirit = spiritCategoryRepository.findById(updatedDrinkDTO.getSpiritCategory())
                    .orElseThrow(() -> new RuntimeException("SpiritCategory not found: " + updatedDrinkDTO.getSpiritCategory()));
            drink.setSpiritCategory(spirit);
        } else {
            drink.setSpiritCategory(null);
        }

        // --- fetch managed ThemeCategory from DB ---
        if (updatedDrinkDTO.getThemeCategory() != null) {
            ThemeCategory theme = themeCategoryRepository.findById(updatedDrinkDTO.getThemeCategory())
                    .orElseThrow(() -> new RuntimeException("ThemeCategory not found: " + updatedDrinkDTO.getThemeCategory()));
            drink.setThemeCategory(theme);
        } else {
            drink.setThemeCategory(null);
        }

        // --- save the drink ---
        drinkRepository.save(drink);

        // --- return updated DrinkDTO ---
        return ResponseEntity.ok(new DrinkResponseDTO(drink));

    }).orElse(ResponseEntity.notFound().build());
}
}

