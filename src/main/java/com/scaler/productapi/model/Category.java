package com.scaler.productapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends BaseModel {
    private String title;
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> products;

    @Override
    public String toString() {
        return "Category{" +
                "title='" + title + '\'' +
                '}';
    }
}
