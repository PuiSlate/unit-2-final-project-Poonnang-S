package com.example.potion_smith_backend.models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;


//Create a table for the Drink model
@Entity
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    Add validation so that the value cannot be blank or null

@NotBlank(message = "Drink name is required")
@Size(min=2, max=80, message = "Drink name must be 2-80 characters long")
    private String drinkName;

    private String drinkInstructions;
    private String drinkIngredients;
    private int imageId;
    private boolean onWeeklyFeature;

    // One drink can have many comments
    @OneToMany(mappedBy = "drink", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();

    // One drink can have many ratings
    @OneToMany(mappedBy = "drink", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Rating> ratings = new ArrayList<>();

    // One drink can have many favorites
    @OneToMany(mappedBy = "drink", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Favorite> favorites = new ArrayList<>();


    public Drink() {};

    public Drink(String drinkName, String drinkInstructions, String drinkIngredients, int imageId, boolean onWeeklyFeature) {
        this.drinkName = drinkName;
        this.drinkInstructions = drinkInstructions;
        this.drinkIngredients = drinkIngredients;
        this.imageId = imageId;
        this.onWeeklyFeature = onWeeklyFeature;
    }

    public int getId() {
        return id;
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

        if (id != drink.id) return false;
        if (imageId != drink.imageId) return false;
        if (onWeeklyFeature != drink.onWeeklyFeature) return false;
        if (!drinkName.equals(drink.drinkName)) return false;
        if (!drinkInstructions.equals(drink.drinkInstructions)) return false;
        return drinkIngredients.equals(drink.drinkIngredients);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + drinkName.hashCode();
        result = 31 * result + drinkInstructions.hashCode();
        result = 31 * result + drinkIngredients.hashCode();
        result = 31 * result + imageId;
        result = 31 * result + (onWeeklyFeature ? 1 : 0);
        return result;
    }
}
