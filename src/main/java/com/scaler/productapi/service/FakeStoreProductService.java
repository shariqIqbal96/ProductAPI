package com.scaler.productapi.service;

import com.scaler.productapi.dto.FakeStoreProductDto;
import com.scaler.productapi.exceptions.ProductNotFoundException;
import com.scaler.productapi.model.Category;
import com.scaler.productapi.model.Product;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private final RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
         FakeStoreProductDto[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products", FakeStoreProductDto[].class
        );
         for (FakeStoreProductDto fsProduct : response) {
             products.add(fsProduct.toProduct());
         }
        return products;
    }

    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException {
        FakeStoreProductDto response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);

        if (response == null) {
            throw new ProductNotFoundException("Product not found with id " + id);
        }
        return response.toProduct();
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto fsProductRequest = getFakeStoreProductDto(product);

        FakeStoreProductDto response = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fsProductRequest,
                FakeStoreProductDto.class
        );

        return response.toProduct();
    }

    private static FakeStoreProductDto getFakeStoreProductDto(Product product) {
        FakeStoreProductDto fsProduct = new FakeStoreProductDto();
        fsProduct.setId(product.getId());
        fsProduct.setTitle(product.getTitle());
        fsProduct.setDescription(product.getDescription());
        fsProduct.setPrice(product.getPrice());
        fsProduct.setImage(product.getImageUrl());
        fsProduct.setCategory(product.getCategory().getTitle());
        return fsProduct;
    }

    @Override
    public Product updateProduct(Product product, long id) {
        FakeStoreProductDto fsProductRequest = getFakeStoreProductDto(product);

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<FakeStoreProductDto> httpEntity = new HttpEntity<>(fsProductRequest, httpHeaders);

        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + id,
                HttpMethod.PUT,
                httpEntity,
                FakeStoreProductDto.class
        );
        return response.getBody().toProduct();
    }

    @Override
    public Product deleteProduct(long id) {

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<FakeStoreProductDto> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + id,
                HttpMethod.DELETE,
                httpEntity,
                FakeStoreProductDto.class
        );

        return response.getBody().toProduct();
    }

    @Override
    public String[] getAllCategories() {

        String[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories", String[].class
        );

        return response;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        List<Product> products = new ArrayList<>();

        FakeStoreProductDto[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + category, FakeStoreProductDto[].class
        );
        for (FakeStoreProductDto fsProduct : response) {
            products.add(fsProduct.toProduct());
        }
        return products;
    }

    @Override
    public Category createCategory(Category category) {
        return null;
    }
}
