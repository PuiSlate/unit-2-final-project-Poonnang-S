package com.example.potion_smith_backend.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



//Create a table for the Drink model
@Entity
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String drinkName;
    private String drinkInstructions;
    private String drinkIngredients;
    private int imageId;
    private boolean onWeeklyFeature;

    public Drink() {};

    public Drink(String drinkName, String drinkInstructions, String drinkIngredients, int imageId, boolean onWeeklyFeature) {
        this.drinkName = drinkName;
        this.drinkInstructions = drinkInstructions;
        this.drinkIngredients = drinkIngredients;
        this.imageId = imageId;
        this.onWeeklyFeature = onWeeklyFeature;
    }

    public int getId() {
        return Id;
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

    @Override
    public String toString() {
        return drinkName + " (" + drinkIngredients + "): " + drinkInstructions + " | Image ID: " + imageId + " | Weekly Feature: " + onWeeklyFeature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Drink drink = (Drink) o;

        if (Id != drink.Id) return false;
        if (imageId != drink.imageId) return false;
        if (onWeeklyFeature != drink.onWeeklyFeature) return false;
        if (!drinkName.equals(drink.drinkName)) return false;
        if (!drinkInstructions.equals(drink.drinkInstructions)) return false;
        return drinkIngredients.equals(drink.drinkIngredients);
    }

    @Override
    public int hashCode() {
        int result = Id;
        result = 31 * result + drinkName.hashCode();
        result = 31 * result + drinkInstructions.hashCode();
        result = 31 * result + drinkIngredients.hashCode();
        result = 31 * result + imageId;
        result = 31 * result + (onWeeklyFeature ? 1 : 0);
        return result;
    }
}
