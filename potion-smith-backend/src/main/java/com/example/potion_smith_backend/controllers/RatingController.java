package com.example.potion_smith_backend.controllers;

import com.example.potion_smith_backend.dtos.RatingDTO;
import com.example.potion_smith_backend.dtos.response.RatingResponseDTO;
import com.example.potion_smith_backend.models.Rating;
import com.example.potion_smith_backend.services.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<RatingResponseDTO> getRatingsByDrinkId(@PathVariable int drinkId) {
        return ratingService.getRatingsByDrinkId(drinkId).stream().map(RatingResponseDTO::new).toList();
    }

    @PostMapping("/add")
    public ResponseEntity<RatingResponseDTO> addRating(@PathVariable int drinkId,
                                                       @RequestBody RatingDTO ratingData) {

        ratingData.setDrinkId(drinkId);
        Rating rating = ratingService.addRating(ratingData);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RatingResponseDTO(rating));
    }
}
