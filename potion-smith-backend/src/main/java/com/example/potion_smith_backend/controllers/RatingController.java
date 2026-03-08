package com.example.potion_smith_backend.controllers;

import com.example.potion_smith_backend.models.Rating;
import com.example.potion_smith_backend.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drinks/{drinkId}/ratings")
public class RatingController {

    private final RatingService ratingService;


    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("")
    public List<Rating> getRatingsByDrinkId(@PathVariable int drinkId) {
        return ratingService.getRatingsByDrinkId(drinkId);
    }

    @PostMapping("")
    public Rating addRating(@PathVariable int drinkId, @RequestBody Rating rating) {
        return ratingService.addRating(drinkId, rating);
    }
}
