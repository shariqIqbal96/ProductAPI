package com.scaler.productapi.controller;

import com.scaler.productapi.dto.ErrorDto;
import com.scaler.productapi.exceptions.ProductNotFoundException;
import com.scaler.productapi.model.Category;
import com.scaler.productapi.model.Product;
import com.scaler.productapi.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(@Qualifier("dbImplementationProductService") ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") long productId) throws ProductNotFoundException {
        return new ResponseEntity<>(productService.getSingleProduct(productId), HttpStatus.OK);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable("id") long productId) {
        return productService.updateProduct(product, productId);
    }

    @DeleteMapping("/products/{id}")
    public Product deleteProduct(@PathVariable("id") long productId) {
        return productService.deleteProduct(productId);
    }

    @GetMapping("/categories")
    public String[] getCategories() {
        return productService.getAllCategories();
    }

    @GetMapping("/products/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable("category") String category) {
        return productService.getProductsByCategory(category);
    }

    @PostMapping("/categories")
    public Category addCategory(@RequestBody Category category) {
        return productService.createCategory(category);
    }

//    @GetMapping("/categories")
//    public String[] getAllCategories() {
//        return productService.getAllCategories();
//    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(Exception exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
