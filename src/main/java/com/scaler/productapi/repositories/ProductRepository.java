package com.scaler.productapi.repositories;

import com.scaler.productapi.model.Category;
import com.scaler.productapi.model.Product;
import com.scaler.productapi.repositories.projections.ProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //Add a new product. To update a product, need to set id in product object
    Product save(Product product);
    //Find product by its title
    List<Product> findByTitle(String title);
    //Find list of products by its category
    List<Product> findByCategory(Category category);
    //Find the product by its id
    Optional<Product> findById(Long id);

    //List<Product> findAll();
    Page<Product> findAll(Pageable pageable);

    @Override
    void deleteById(Long id);

    //Native query implementation
    @Query(value = "select p.id, p.description from product p where p.category_id = :categoryId", nativeQuery = true)
    List<ProductProjection> getProductByCategoryId(@Param("categoryId") Long caretoryId);
}
