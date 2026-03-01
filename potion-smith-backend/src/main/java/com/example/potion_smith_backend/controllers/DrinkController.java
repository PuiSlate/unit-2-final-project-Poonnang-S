package com.example.potion_smith_backend.controllers;

import com.example.potion_smith_backend.models.Drink;
import com.example.potion_smith_backend.repositories.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping
public class DrinkController {

    @Autowired
    DrinkRepository drinkRepository;

    //    Retrieve all drinks from database
//    GET http://localhost:8080/drinks
    @GetMapping("")
    public List<?> getAllDrinks() {
        return drinkRepository.findAll();
    }

    //    Retrieve a specific drink object using its id
//    GET http://localhost:8080/drinks/{id}
    @GetMapping("/details/{drinkId}")
    public Drink getDrinkById(@PathVariable int drinkId) {
        return drinkRepository.findById(drinkId).orElse(null);
    }

//    Save a new drink to the database
//    Uses query parameters for dynamic results
//    POST http://localhost:8080/drinks/create?drinkName={name}&drink
@PostMapping("/add")
    public String addNewDrink(@RequestParam String drinkName, @RequestParam String drinkInstructions, @RequestParam String drinkIngredients, @RequestParam int imageId, @RequestParam boolean onWeeklyFeature) {
        Drink newDrink = new Drink(drinkName, drinkInstructions, drinkIngredients, imageId, onWeeklyFeature);
        drinkRepository.save(newDrink);
        return "Drink added: " + newDrink;
    }

}
