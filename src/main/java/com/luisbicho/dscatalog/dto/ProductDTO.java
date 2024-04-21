package com.luisbicho.dscatalog.dto;

import com.luisbicho.dscatalog.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ProductDTO {

    private Long id;
    @NotBlank(message = "Required feld")
    private String name;
    @Positive(message = "Must be positive")
    private Double price;
    @NotBlank(message = "Required feld")
    private String description;
    private String imgUrl;


    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        description = product.getDescription();
        imgUrl = product.getImgUrl();
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

}
