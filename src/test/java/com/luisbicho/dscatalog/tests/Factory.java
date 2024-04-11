package com.luisbicho.dscatalog.tests;

import com.luisbicho.dscatalog.dto.ProductWithCategoryDTO;
import com.luisbicho.dscatalog.entities.Category;
import com.luisbicho.dscatalog.entities.Product;

public class Factory {

    public static Product createProduct() {
        Product product = new Product(1L, "The Lord of the Rings", "Lorem ipsum dod do eiusmodlaborum.", 90.5, "https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg");
        product.getCategories().add(createCategory());
        return product;
    }

    public static ProductWithCategoryDTO createProductWithCategoryDTO() {
        Product product = createProduct();
        return new ProductWithCategoryDTO(product);
    }

    public static Category createCategory() {
        return new Category(1L, "Electronics");
    }
}
