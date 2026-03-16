package com.example.potion_smith_backend.dtos.response;

import com.example.potion_smith_backend.models.Rating;

public class RatingResponseDTO {

    private int id;
    private int stars;
    private int userId;
    private String userName;

    public RatingResponseDTO() {}

    public RatingResponseDTO(int id, int stars, int userId, String userName) {
        this.id = id;
        this.stars = stars;
        this.userId = userId;
        this.userName = userName;
    }

    public RatingResponseDTO(Rating rating) {
        this.id = rating.getId();
        this.stars = rating.getStars();
        this.userId = rating.getUser().getId();
        this.userName = rating.getUser().getUsername();
    }

    public int getId() {
        return id;
    }

    public int getStars() {
        return stars;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}