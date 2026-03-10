package com.example.potion_smith_backend.controllers;


import com.example.potion_smith_backend.dtos.CommentDTO;
import com.example.potion_smith_backend.models.Comment;
import com.example.potion_smith_backend.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drinks/{drinkId}/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("")
    public List<Comment> getCommentsByDrinkId(@PathVariable int drinkId) {
        return commentService.getCommentsByDrinkId(drinkId);
    }

    @PostMapping("")
    public Comment addComment(@PathVariable int drinkId, @RequestBody CommentDTO commentData) {
        Comment comment = new Comment(commentData.getCommentText(), commentData.getUserId(), commentData.getDrinkId());
        return commentService.addComment(drinkId, comment);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable int drinkId, @PathVariable int commentId) {
        commentService.deleteComment(drinkId, commentId);

    }
}
