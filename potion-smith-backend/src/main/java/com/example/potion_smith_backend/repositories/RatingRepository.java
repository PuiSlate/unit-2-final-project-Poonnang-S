package com.example.potion_smith_backend.repositories;

import com.example.potion_smith_backend.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

    List<Rating> findByDrink_Id(int drinkId);

    List<Rating> findByUser_Id(int userId);

    Optional<Rating> findByUser_IdAndDrink_Id(int userId, int drinkId);
}