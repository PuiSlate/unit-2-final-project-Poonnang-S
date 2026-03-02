package com.example.potion_smith_backend.controllers;

import com.example.potion_smith_backend.models.Drink;
import com.example.potion_smith_backend.repositories.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
        Drink drink = drinkRepository.findById(drinkId).orElse(null);
        return new ResponseEntity<>(drink, HttpStatus.OK); // 200
    }

    //     Save a new drink to the database
//    refactored from RequestParams to a RESTful controller by using @RequestBody to accept JSON
//    for POST requests, persisted data using Spring Data JPA, and returned proper HTTP status codes using ResponseEntity.
//    POST http://localhost:8080/api/drinks/add
//Ensure the mapping is configured to consume JSON instead of using query params
    @PostMapping(value= "/add", consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewDrink(@RequestBody Drink drink) {
        drinkRepository.save(drink);
        return new ResponseEntity<>(drink, HttpStatus.CREATED); //201

    }



    //    Delete a drink from the database
//    DELETE http://localhost:8080/api/drinks/3 (for example)
    @DeleteMapping("/{drinkId}")
    public ResponseEntity<Void> deleteDrink(@PathVariable int drinkId) {
        return drinkRepository.findById(drinkId)
                .map(drink -> {
                    drinkRepository.delete(drink);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
}


//    @PostMapping("/add")
//    public String addNewDrink(@RequestParam String drinkName, @RequestParam String drinkInstructions, @RequestParam String drinkIngredients, @RequestParam int imageId, @RequestParam boolean onWeeklyFeature) {
//        Drink newDrink = new Drink(drinkName, drinkInstructions, drinkIngredients, imageId, onWeeklyFeature);
//        drinkRepository.save(newDrink);
//        return "Drink added: " + newDrink;