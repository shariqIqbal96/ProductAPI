package com.scaler.productapi;

import com.scaler.productapi.model.Product;
import com.scaler.productapi.repositories.ProductRepository;
import com.scaler.productapi.repositories.projections.ProductProjection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductApiApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testingQueries(){
        List<ProductProjection> products = productRepository.getProductByCategoryId(1L);
        System.out.println(products.get(0).getId());
        System.out.println(products.get(0).getDescription());
    }
}
