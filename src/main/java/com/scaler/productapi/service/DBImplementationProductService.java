package com.scaler.productapi.service;

import com.scaler.productapi.exceptions.ProductNotFoundException;
import com.scaler.productapi.model.Category;
import com.scaler.productapi.model.Product;
import com.scaler.productapi.repositories.CategoryRepository;
import com.scaler.productapi.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("dbImplementationProductService")
public class DBImplementationProductService implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public DBImplementationProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new ProductNotFoundException("Product not found with id " + id);
    }

    @Override
    public Product createProduct(Product product) {
        Category category = categoryRepository.findByTitle(product.getCategory().getTitle());
        if (category != null) {
            product.setCategory(category);
        }
        else {
            Category newCategory = new Category();
            newCategory.setTitle(product.getCategory().getTitle());
            product.setCategory(newCategory);
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, long id) {
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(long id) {
        productRepository.deleteById(id);
        return null;
    }

    @Override
    public String[] getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        String[] categoryList = new String[categories.size()];
        for (int i = 0; i < categories.size(); i++) {
            categoryList[i] = categories.get(i).getTitle();
        }
        return categoryList;
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {
        Category category = categoryRepository.findByTitle(categoryName);
        Category categoryRequestObj = new Category();
        categoryRequestObj.setTitle(category.getTitle());
        categoryRequestObj.setId(category.getId());
        return productRepository.findByCategory(categoryRequestObj);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
}
