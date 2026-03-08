package com.example.potion_smith_backend.controllers;

import com.example.potion_smith_backend.models.Favorite;
import com.example.potion_smith_backend.services.FavoriteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drinks/{drinkId}/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping
    public List<Favorite> getFavoritesByDrinkId(@PathVariable int drinkId) {
        return favoriteService.getFavoritesByDrinkId(drinkId);
    }

    @PostMapping
    public Favorite addFavorite(@PathVariable int drinkId, @RequestBody Favorite favorite) {
        return favoriteService.addFavorite(favorite);
    }

    @DeleteMapping("/{id}")
    public void removeFavorite(@PathVariable int id) {
        favoriteService.removeFavorite(id);
    }
}
