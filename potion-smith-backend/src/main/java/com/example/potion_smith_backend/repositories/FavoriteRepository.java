package com.example.potion_smith_backend.repositories;

import com.example.potion_smith_backend.models.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUserId(int userId);
    List<Favorite> findByDrinkId(int drinkId);
}
