package com.example.potion_smith_backend.models;

public class Drink {
    private final int drinkId;
    private String drinkName;
    private String drinkInstructions;
    private String drinkIngredients;
    private int imageId;
    private boolean onWeeklyFeature;

    public Drink(int drinkId, String drinkName, String drinkInstructions, String drinkIngredients, int imageId, boolean onWeeklyFeature) {
        this.drinkId = drinkId;
        this.drinkName = drinkName;
        this.drinkInstructions = drinkInstructions;
        this.drinkIngredients = drinkIngredients;
        this.imageId = imageId;
        this.onWeeklyFeature = onWeeklyFeature;
    }

    public int getDrinkId() {
        return drinkId;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getDrinkInstructions() {
        return drinkInstructions;
    }

    public void setDrinkInstructions(String drinkInstructions) {
        this.drinkInstructions = drinkInstructions;
    }

    public String getDrinkIngredients() {
        return drinkIngredients;
    }

    public void setDrinkIngredients(String drinkIngredients) {
        this.drinkIngredients = drinkIngredients;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public boolean isOnWeeklyFeature() {
        return onWeeklyFeature;
    }

    public void setOnWeeklyFeature(boolean onWeeklyFeature) {
        this.onWeeklyFeature = onWeeklyFeature;
    }
}
