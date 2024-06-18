package com.scaler.productapi.service;

import com.scaler.productapi.exceptions.ProductNotFoundException;
import com.scaler.productapi.model.Category;
import com.scaler.productapi.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product getSingleProduct(long id) throws ProductNotFoundException;
    public Product createProduct(Product product);
    public Product updateProduct(long id, Product product);
    public Product deleteProduct(long id);
    public String[] getAllCategories();
    public List<Product> getProductsByCategory(String category);
}
