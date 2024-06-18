package com.scaler.productapi.dto;

import com.scaler.productapi.model.Category;
import com.scaler.productapi.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private long id;
    private String title;
    private double price;
    private  String category;
    private String description;
    private String image;

    public Product toProduct() {
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(image);

        Category c = new Category();
        c.setTitle(category);
        product.setCategory(c);
        return product;
    }
}
