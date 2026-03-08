package com.example.potion_smith_backend.services;

import com.example.potion_smith_backend.models.Drink;
import com.example.potion_smith_backend.models.Rating;
import com.example.potion_smith_backend.repositories.DrinkRepository;
import com.example.potion_smith_backend.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final DrinkRepository drinkRepository;

    public RatingService(RatingRepository ratingRepository, DrinkRepository drinkRepository) {
        this.ratingRepository = ratingRepository;
        this.drinkRepository = drinkRepository;
    }

    public List<Rating> getRatingsByDrinkId(int drinkId) {
        return ratingRepository.findByDrinkId(drinkId);
    }

    public Rating addRating(int drinkId, Rating rating) {

    Drink drink = drinkRepository.findById(drinkId)
                .orElseThrow(() -> new RuntimeException("Drink not found with id: " + drinkId));
        rating.setStars(drink);
        return ratingRepository.save(rating);
    }
}
