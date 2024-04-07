package com.luisbicho.dscatalog.dto;

import com.luisbicho.dscatalog.entities.Product;

public class ProductDTO {

    private Long id;
    private String name;
    private Double price;
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
