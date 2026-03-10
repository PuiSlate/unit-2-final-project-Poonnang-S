package com.example.potion_smith_backend.services;

import com.example.potion_smith_backend.models.Comment;
import com.example.potion_smith_backend.models.Drink;
import com.example.potion_smith_backend.repositories.CommentRepository;
import com.example.potion_smith_backend.repositories.DrinkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final DrinkRepository drinkRepository;

    public CommentService(CommentRepository commentRepository, DrinkRepository drinkRepository) {
        this.commentRepository = commentRepository;
        this.drinkRepository = drinkRepository;
    }

    public List<Comment> getCommentsByDrinkId(int drinkId) {
        return commentRepository.findByDrinkId(drinkId);
    }

    public Comment addComment(int drinkId, Comment comment) {
        Drink drink = drinkRepository.findById(drinkId)
                .orElseThrow(() -> new RuntimeException("Drink not found with id: " + drinkId));
        comment.setDrink(drink);
        return commentRepository.save(comment);
    }

    public void deleteComment(int drinkId, int commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));
        if (comment.getDrink().getId() != drinkId) {
            throw new RuntimeException("Comment with id: " + commentId + " does not belong to drink with id: " + drinkId);
        }
        commentRepository.delete(comment);
    }
}
