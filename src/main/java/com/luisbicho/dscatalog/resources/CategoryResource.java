package com.luisbicho.dscatalog.resources;

import com.luisbicho.dscatalog.entities.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    
    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "Books"));
        categories.add(new Category(2L, "Electronics"));
        categories.add(new Category(3L, "Computers"));
        return ResponseEntity.ok(categories);
    }

}
