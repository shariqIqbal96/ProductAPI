package com.scaler.productapi.dto;

import com.scaler.productapi.model.Category;

public class FakeStoreCategoryDto {
    private String categoryName;

    public Category toCategory() {
        Category category = new Category();
        category.setTitle(categoryName);
        return category;
    }
}
