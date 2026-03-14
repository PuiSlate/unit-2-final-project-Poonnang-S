package com.example.potion_smith_backend.dtos;

import com.example.potion_smith_backend.models.Drink;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DrinkDTO {

    @NotBlank(message = "Drink name is required")
    @Size(min=2, max=80, message = "Drink name must be 2-80 characters long")
    private String drinkName;

    private String drinkInstructions;
    private String drinkIngredients;
    private String imageId;
    private boolean onWeeklyFeature;

    // Existing constructor for POST requests
    public DrinkDTO(String drinkName, String drinkInstructions, String drinkIngredients, String imageId, boolean onWeeklyFeature) {
        this.drinkName = drinkName;
        this.drinkInstructions = drinkInstructions;
        this.drinkIngredients = drinkIngredients;
        this.imageId = imageId;
        this.onWeeklyFeature = onWeeklyFeature;
    }

    // NEW: Constructor to convert a Drink entity to DTO (for GET endpoints)
    public DrinkDTO(Drink drink) {
        this.drinkName = drink.getDrinkName();
        this.drinkInstructions = drink.getDrinkInstructions();
        this.drinkIngredients = drink.getDrinkIngredients();
        this.imageId = drink.getImageId();
        this.onWeeklyFeature = drink.isOnWeeklyFeature();
    }

    // Getters & Setters
    public String getDrinkName() { return drinkName; }
    public void setDrinkName(String drinkName) { this.drinkName = drinkName; }
    public String getDrinkInstructions() { return drinkInstructions; }
    public void setDrinkInstructions(String drinkInstructions) { this.drinkInstructions = drinkInstructions; }
    public String getDrinkIngredients() { return drinkIngredients; }
    public void setDrinkIngredients(String drinkIngredients) { this.drinkIngredients = drinkIngredients; }
    public String getImageId() { return imageId; }
    public void setImageId(String imageId) { this.imageId = imageId; }
    public boolean isOnWeeklyFeature() { return onWeeklyFeature; }
    public void setOnWeeklyFeature(boolean onWeeklyFeature) { this.onWeeklyFeature = onWeeklyFeature; }
}