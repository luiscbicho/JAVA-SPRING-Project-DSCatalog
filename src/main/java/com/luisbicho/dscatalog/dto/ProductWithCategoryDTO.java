package com.luisbicho.dscatalog.dto;

import com.luisbicho.dscatalog.entities.Category;
import com.luisbicho.dscatalog.entities.Product;

import java.util.HashSet;
import java.util.Set;

public class ProductWithCategoryDTO {

    private Long id;
    private String name;
    private Double price;
    private String description;
    private String imgUrl;

    private Set<CategoryDTO> categories = new HashSet<>();


    public ProductWithCategoryDTO() {
    }

    public ProductWithCategoryDTO(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        description = product.getDescription();
        imgUrl = product.getImgUrl();
        for (Category category : product.getCategories()) {
            categories.add(new CategoryDTO(category));
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Set<CategoryDTO> getCategories() {
        return categories;
    }

}
