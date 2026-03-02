package com.example.potion_smith_backend.repositories;

import com.example.potion_smith_backend.models.ThemeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeCategoryRepository extends JpaRepository<ThemeCategory, Integer> {

}
