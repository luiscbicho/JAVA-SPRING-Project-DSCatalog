package com.luisbicho.dscatalog.dto;

import com.luisbicho.dscatalog.entities.Category;

public class CategoryDTO {

    private Long id;
    private String name;


    public CategoryDTO() {
    }

    public CategoryDTO(Category category) {
        id = category.getId();
        name = category.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
