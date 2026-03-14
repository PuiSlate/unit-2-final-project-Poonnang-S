package com.example.potion_smith_backend.controllers;

import com.example.potion_smith_backend.dtos.DrinkDTO;
import com.example.potion_smith_backend.models.Drink;
import com.example.potion_smith_backend.repositories.DrinkRepository;
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

    //    Retrieve all drinks from database
    // refactored to return a ResponseEntity object with an HttpStatus of 200 OK
//    GET http://localhost:8080/api/drinks
    @GetMapping("")
    public ResponseEntity<?> getAllDrinks() {
        List<Drink> allDrinks = drinkRepository.findAll();
        return new ResponseEntity<>(allDrinks, HttpStatus.OK); //200
    }

    //    Retrieve a specific drink object using its id
//    refactored to return a ResponseEntity object with an HttpStatus of 200 OK
//    GET http://localhost:8080/api/drinks/details/3 (for example)
    @GetMapping("/details/{drinkId}")
    public ResponseEntity<?> getDrinkById(@PathVariable int drinkId) {
        return drinkRepository.findById(drinkId)
                .map(drink -> new ResponseEntity<>(drink, HttpStatus.OK)) //200
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); //404
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
    @DeleteMapping("/drinks/{id}")
    public ResponseEntity<Void> deleteDrink(@PathVariable int drinkId) {
        return drinkRepository.findById(drinkId)
                .map(drink -> {
                    drinkRepository.delete(drink);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

//    Put endpoint to update an existing drink in the database
//    PUT http://localhost:8080/api/drinks/update/3 (for example)
@PutMapping("/drinks/{id}")
public ResponseEntity<Drink> updateDrink(@PathVariable int id, @RequestBody Drink updatedDrink) {
    return drinkRepository.findById(id).map(drink -> {
        drink.setDrinkName(updatedDrink.getDrinkName());
        drink.setDrinkIngredients(updatedDrink.getDrinkIngredients());
        drink.setDrinkInstructions(updatedDrink.getDrinkInstructions());
        drink.setImageId(updatedDrink.getImageId());
        drink.setOnWeeklyFeature(updatedDrink.isOnWeeklyFeature());
        drinkRepository.save(drink);
        return ResponseEntity.ok(drink);
    }).orElse(ResponseEntity.notFound().build());
}
}

