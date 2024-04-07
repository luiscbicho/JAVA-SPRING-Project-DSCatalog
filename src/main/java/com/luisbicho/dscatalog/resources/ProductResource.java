package com.luisbicho.dscatalog.resources;

import com.luisbicho.dscatalog.dto.ProductDTO;
import com.luisbicho.dscatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAllPaged(Pageable pageable) {
        Page<ProductDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

}
