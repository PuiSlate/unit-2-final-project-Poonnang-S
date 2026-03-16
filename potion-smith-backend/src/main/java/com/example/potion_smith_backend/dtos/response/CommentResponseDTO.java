package com.example.potion_smith_backend.dtos.response;

import com.example.potion_smith_backend.models.Comment;

import java.time.LocalDateTime;

public class CommentResponseDTO {

    private int id;
    private String commentText;
    private String username;
    private LocalDateTime createdAt;

    public CommentResponseDTO(Comment comment) {
        this.id = comment.getId();
        this.commentText = comment.getCommentText();
        this.username = comment.getUser().getUsername();
        this.createdAt = comment.getCreatedAt();
    }

    public CommentResponseDTO(Object o) {
    }

    public int getId() {
        return id;
    }

    public String getCommentText() {
        return commentText;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}


