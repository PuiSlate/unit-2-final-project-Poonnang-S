package com.example.potion_smith_backend.dtos.response;

public class DrinkResponseDTO {
    private String drinkName;
    private String drinkIngredients;
    private String drinkInstructions;
    private String imageId;
    private String spiritCategoryTitle;

    public DrinkResponseDTO(String drinkName, String drinkIngredients, String drinkInstructions, String imageId, String spiritCategoryTitle) {
        this.drinkName = drinkName;
        this.drinkIngredients = drinkIngredients;
        this.drinkInstructions = drinkInstructions;
        this.imageId = imageId;
        this.spiritCategoryTitle = spiritCategoryTitle;
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
}
