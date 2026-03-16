package com.example.potion_smith_backend.dtos.response;
import com.example.potion_smith_backend.models.Rating;

public class RatingResponseDTO {

        private int id;
        private int stars;
        private int userId;
        private int drinkId;

        public RatingResponseDTO() {
        }

        public RatingResponseDTO(int id, int stars, int userId, int drinkId) {
            this.id = id;
            this.stars = stars;
            this.userId = userId;
            this.drinkId = drinkId;
        }

    public RatingResponseDTO(Rating rating) {
        this.id = rating.getId();
        this.stars = rating.getStars();
        this.userId = rating.getUserId();
        this.drinkId = rating.getDrinkId();
    }

    //        Getters
        public int getId() {
            return id;
        }

        public int getStars() {
            return stars;
        }

        public int getUserId() {
            return userId;
        }

        public int getDrinkId() {
            return drinkId;
        }
}
