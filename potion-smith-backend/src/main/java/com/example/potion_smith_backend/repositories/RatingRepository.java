package com.example.potion_smith_backend.repositories;

import com.example.potion_smith_backend.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findByDrinkId(int drinkId);
}
