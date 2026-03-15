package com.example.potion_smith_backend.dtos.response;

import com.example.potion_smith_backend.models.Drink;

public class DrinkResponseDTO {
    private String drinkName;
    private String drinkIngredients;
    private String drinkInstructions;
    private String imageId;
    private String spiritCategoryTitle;
    private String themeCategoryTitle;

    public DrinkResponseDTO(Drink drink) {
        this.drinkName = drink.getDrinkName();
        this.drinkIngredients = drink.getDrinkIngredients();
        this.drinkInstructions = drink.getDrinkInstructions()
        this.imageId = drink.getImageId()
        this.spiritCategoryTitle = drink.getDrinkIngredients() != null
                ? drink.getSpiritCategory().getTitle()
                : null;
        this.themeCategoryTitle = drink.getThemeCategory() != null
                ? drink.getThemeCategory().getTitle()
                : null;
    }

//    Getters & Setters
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
}
