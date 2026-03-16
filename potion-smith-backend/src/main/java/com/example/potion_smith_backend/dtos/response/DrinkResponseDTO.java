package com.example.potion_smith_backend.dtos.response;

import com.example.potion_smith_backend.models.Drink;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DrinkResponseDTO {
    private int id;
    private String drinkName;
    private String drinkIngredients;
    private String drinkInstructions;
    private String imageId;
    private String spiritCategoryTitle;
    private String themeCategoryTitle;
    private List<CommentResponseDTO> comments;
    private List<RatingResponseDTO> ratings;
    private boolean onWeeklyFeature;

    public DrinkResponseDTO(Drink drink) {
        this.id = drink.getId();
        this.drinkName = drink.getDrinkName();
        this.drinkIngredients = drink.getDrinkIngredients();
        this.drinkInstructions = drink.getDrinkInstructions();
        this.imageId = drink.getImageId();
        this.onWeeklyFeature = drink.isOnWeeklyFeature();

        this.spiritCategoryTitle = drink.getSpiritCategory() != null
                ? drink.getSpiritCategory().getTitle()
                : null;

        this.themeCategoryTitle = drink.getThemeCategory() != null
                ? drink.getThemeCategory().getTitle()
                : null;

        this.comments = new ArrayList<>();
        if (drink.getComments() != null) {
            for (Arrays comment : Arrays.asList(drink.getComments())) {
                this.comments.add(new CommentResponseDTO(comment));
            }
        }

        this.ratings = new ArrayList<>();
        if (drink.getRatings() != null) {
            for (Arrays rating : Arrays.asList(drink.getRatings())) {
                this.ratings.add(new RatingResponseDTO(rating));
            }
        }
    }

    // ----------------------------
    // Getters & Setters
    // ----------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getDrinkIngredients() {
        return drinkIngredients;
    }

    public void setDrinkIngredients(String drinkIngredients) {
        this.drinkIngredients = drinkIngredients;
    }

    public String getDrinkInstructions() {
        return drinkInstructions;
    }

    public void setDrinkInstructions(String drinkInstructions) {
        this.drinkInstructions = drinkInstructions;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getSpiritCategoryTitle() {
        return spiritCategoryTitle;
    }

    public void setSpiritCategoryTitle(String spiritCategoryTitle) {
        this.spiritCategoryTitle = spiritCategoryTitle;
    }

    public String getThemeCategoryTitle() {
        return themeCategoryTitle;
    }

    public void setThemeCategoryTitle(String themeCategoryTitle) {
        this.themeCategoryTitle = themeCategoryTitle;
    }

    public List<CommentResponseDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponseDTO> comments) {
        this.comments = comments;
    }

    public List<RatingResponseDTO> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingResponseDTO> ratings) {
        this.ratings = ratings;
    }

    public void setOnWeeklyFeature(boolean onWeeklyFeature) {
        this.onWeeklyFeature = onWeeklyFeature;
    }

    public boolean isOnWeeklyFeature() {
        return onWeeklyFeature;

    }
}