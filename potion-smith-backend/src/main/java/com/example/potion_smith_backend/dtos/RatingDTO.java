package com.example.potion_smith_backend.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RatingDTO {

    @NotNull(message = "Stars are required")
    @Min(1)
    @Max(5)
    private Integer stars;

    @NotNull(message = "User ID is required")
    private Integer userId;

    @NotNull(message = "Drink ID is required")
    private Integer drinkId;

    // Default constructor needed by Jackson
    public RatingDTO() {}

    public RatingDTO(Integer stars, Integer userId, Integer drinkId) {
        this.stars = stars;
        this.userId = userId;
        this.drinkId = drinkId;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(Integer drinkId) {
        this.drinkId = drinkId;
    }
}