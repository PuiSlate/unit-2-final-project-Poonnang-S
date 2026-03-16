package com.example.potion_smith_backend.controllers;


import com.example.potion_smith_backend.dtos.CommentDTO;
import com.example.potion_smith_backend.dtos.response.CommentResponseDTO;
import com.example.potion_smith_backend.models.Comment;
import com.example.potion_smith_backend.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;


@RestController
@RequestMapping("/api/drinks/{drinkId}/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("")
    public List<CommentResponseDTO> getCommentsByDrinkId(@PathVariable int drinkId) {
        return commentService.getCommentsByDrinkId(drinkId).stream()
        .map(CommentResponseDTO::new).toList();
    }

    private <T> Stream stream() {
        return null;
    }

    @PostMapping("")
    public Comment addComment(@PathVariable int drinkId, @RequestBody CommentDTO commentData) {
        return commentService.addCommentWithUser(drinkId, commentData);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable int drinkId, @PathVariable int commentId) {
        commentService.deleteComment(drinkId, commentId);

    }
}
