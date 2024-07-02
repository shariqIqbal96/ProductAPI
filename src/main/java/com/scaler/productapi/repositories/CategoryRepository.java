package com.scaler.productapi.repositories;

import com.scaler.productapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category save(Category category);
    Category findByTitle(String title);
    List<Category> findAll();
}
