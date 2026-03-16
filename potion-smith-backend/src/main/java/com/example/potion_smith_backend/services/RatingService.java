package com.example.potion_smith_backend.services;

import com.example.potion_smith_backend.dtos.RatingDTO;
import com.example.potion_smith_backend.models.Drink;
import com.example.potion_smith_backend.models.Rating;
import com.example.potion_smith_backend.models.User;
import com.example.potion_smith_backend.repositories.DrinkRepository;
import com.example.potion_smith_backend.repositories.RatingRepository;
import com.example.potion_smith_backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final DrinkRepository drinkRepository;
    private final UserRepository userRepository;

    public RatingService(RatingRepository ratingRepository,
                         DrinkRepository drinkRepository,
                         UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.drinkRepository = drinkRepository;
        this.userRepository = userRepository;
    }

    public List<Rating> getRatingsByDrinkId(int drinkId) {
        return ratingRepository.findByDrinkId(drinkId);
    }

    public Rating addRating(RatingDTO ratingDTO) {

        Drink drink = drinkRepository.findById(ratingDTO.getDrinkId())
                .orElseThrow(() -> new RuntimeException("Drink not found with id: " + ratingDTO.getDrinkId()));

        User user = userRepository.findById(ratingDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + ratingDTO.getUserId()));

        Rating rating = new Rating();
        rating.setStars(ratingDTO.getStars());
        rating.setDrink(drink);
        rating.setUser(user);

        return ratingRepository.save(rating);
    }

    public List<Rating> getRatingsByUser(int userId) {
        return ratingRepository.findByUserId(userId);
    }
}