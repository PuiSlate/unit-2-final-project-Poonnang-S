package com.example.potion_smith_backend.controllers;

import com.example.potion_smith_backend.models.Drink;
import com.example.potion_smith_backend.repositories.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/drinks")
public class DrinkController {

    @Autowired
    DrinkRepository drinkRepository;

    //    Retrieve all drinks from database
//    GET http://localhost:8080/api/drinks
    @GetMapping("")
    public List<?> getAllDrinks() {
        return drinkRepository.findAll();
    }

    //    Retrieve a specific drink object using its id
//    GET http://localhost:8080/api/drinks/details/3 (for example)
    @GetMapping("/details/{drinkId}")
    public Drink getDrinkById(@PathVariable int drinkId) {
        return drinkRepository.findById(drinkId).orElse(null);
    }

    //    Save a new drink to the database
//    refactored from RequesParams to a RESTful controller by mapping the base path to /api/drinks, used @RequestBody to accept JSON
//    for POST requests, persisted data using Spring Data JPA, and returned proper HTTP status codes using ResponseEntity.
//    POST http://localhost:8080/api/drinks/

    @PostMapping
    public ResponseEntity<Drink> addNewDrink(@RequestBody Drink drink) {
        Drink savedDrink = drinkRepository.save(drink);
        return new ResponseEntity<>(savedDrink, HttpStatus.CREATED);

    }

//    Update the existing drink in the database
//    PUT http://localhost:8080/api/drinks/3 (for example)



//    Delete a drink from the database
//    DELETE http://localhost:8080/api/drinks/3 (for example)
}


//    @PostMapping("/add")
//    public String addNewDrink(@RequestParam String drinkName, @RequestParam String drinkInstructions, @RequestParam String drinkIngredients, @RequestParam int imageId, @RequestParam boolean onWeeklyFeature) {
//        Drink newDrink = new Drink(drinkName, drinkInstructions, drinkIngredients, imageId, onWeeklyFeature);
//        drinkRepository.save(newDrink);
//        return "Drink added: " + newDrink;