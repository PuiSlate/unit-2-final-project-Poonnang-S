package com.example.potion_smith_backend.repositories;

import com.example.potion_smith_backend.models.SpiritCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpiritCategoryRepository extends JpaRepository<SpiritCategory, Integer> {
}
