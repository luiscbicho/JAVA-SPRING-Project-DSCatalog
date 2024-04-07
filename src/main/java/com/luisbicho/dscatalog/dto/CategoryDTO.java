package com.luisbicho.dscatalog.dto;

import com.luisbicho.dscatalog.entities.Category;
import com.luisbicho.dscatalog.entities.Product;

import java.util.HashSet;
import java.util.Set;

public class CategoryDTO {

    private Long id;
    private String name;

    private Set<ProductDTO> products = new HashSet<>();


    public CategoryDTO() {
    }

    public CategoryDTO(Category category) {
        id = category.getId();
        name = category.getName();
        for (Product x : category.getProducts()) {
            products.add(new ProductDTO(x));
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<ProductDTO> getProducts() {
        return products;
    }
}
