package com.example.potion_smith_backend.services;

import com.example.potion_smith_backend.models.Favorite;
import com.example.potion_smith_backend.repositories.FavoriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public List<Favorite> getFavoritesByUserId(int userId) {
        return favoriteRepository.findByUserId(userId);
    }

    public List<Favorite> getFavoritesByDrinkId(int drinkId) {
        return favoriteRepository.findByDrinkId(drinkId);
    }

    public Favorite addFavorite(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    public void removeFavorite(int id) {
        favoriteRepository.deleteById(id);
    }
}
