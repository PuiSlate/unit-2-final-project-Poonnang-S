package com.example.potion_smith_backend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CommentDTO {

    @NotBlank(message = "Comment text is required")
    private String commentText;

    @NotNull(message = "User ID is required")
    private int userId;

    @NotNull(message = "Drink ID is required")
    private int drinkId;

    public CommentDTO(String commentText, int userId, int drinkId) {
        this.commentText = commentText;
        this.userId = userId;
        this.drinkId = drinkId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
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
