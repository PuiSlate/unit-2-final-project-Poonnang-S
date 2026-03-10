package com.example.potion_smith_backend.repositories;

import com.example.potion_smith_backend.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByDrinkId(int drinkId);

}
