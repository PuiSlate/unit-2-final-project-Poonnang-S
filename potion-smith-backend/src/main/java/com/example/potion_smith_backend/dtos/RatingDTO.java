package com.example.potion_smith_backend.dtos;

public class RatingDTO {
    private int stars;
    private int userId;
    private int drinkId;

    public RatingDTO(int stars, int userId, int drinkId) {
        this.stars = stars;
        this.userId = userId;
        this.drinkId = drinkId;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }


}
