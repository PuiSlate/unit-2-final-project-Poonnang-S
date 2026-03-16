package com.example.potion_smith_backend.services;

import com.example.potion_smith_backend.dtos.CommentDTO;
import com.example.potion_smith_backend.models.Comment;
import com.example.potion_smith_backend.models.Drink;
import com.example.potion_smith_backend.models.User;
import com.example.potion_smith_backend.repositories.CommentRepository;
import com.example.potion_smith_backend.repositories.DrinkRepository;
import com.example.potion_smith_backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final DrinkRepository drinkRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, DrinkRepository drinkRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.drinkRepository = drinkRepository;
        this.userRepository = userRepository;
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

//   This method adds a comment to a drink and associates it with a user.
//   It first retrieves the user and drink from their respective repositories using the IDs
//   provided in the CommentDTO. If either the user or drink is not found, it throws an exception.
//   Then it creates a new Comment object, sets its text, user, and drink, and saves it to the database using the CommentRepository.
    public Comment addCommentWithUser(int drinkId, CommentDTO commentDTO) {
        User user = userRepository.findById(commentDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Drink drink = drinkRepository.findById(drinkId)
                .orElseThrow(() -> new RuntimeException("Drink not found"));

        Comment comment = new Comment();
        comment.setCommentText(commentDTO.getCommentText());
        comment.setUser(user);
        comment.setDrink(drink);

        return commentRepository.save(comment);
    }
}
