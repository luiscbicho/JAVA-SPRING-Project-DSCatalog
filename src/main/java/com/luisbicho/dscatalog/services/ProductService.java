package com.luisbicho.dscatalog.services;

import com.luisbicho.dscatalog.dto.CategoryDTO;
import com.luisbicho.dscatalog.dto.ProductWithCategoryDTO;
import com.luisbicho.dscatalog.entities.Category;
import com.luisbicho.dscatalog.entities.Product;
import com.luisbicho.dscatalog.repositories.CategoryRepository;
import com.luisbicho.dscatalog.repositories.ProductRepository;
import com.luisbicho.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductWithCategoryDTO> findAllPaged(Pageable pageable) {
        Page<Product> result = repository.findAll(pageable);
        return result.map(x -> new ProductWithCategoryDTO(x));
    }

    @Transactional(readOnly = true)
    public ProductWithCategoryDTO findById(Long id) {
        Optional<Product> result = repository.findById(id);
        return new ProductWithCategoryDTO(result.orElseThrow(() -> new ResourceNotFoundException("Product not found")));
    }

    @Transactional
    public ProductWithCategoryDTO insert(ProductWithCategoryDTO dto) {
        Product product = new Product();
        update(product, dto);
        product = repository.save(product);
        return new ProductWithCategoryDTO(product);

    }

    @Transactional
    public ProductWithCategoryDTO update(Long id, ProductWithCategoryDTO dto) {
        try {
            Product product = repository.getReferenceById(id);
            update(product, dto);
            product = repository.save(product);
            return new ProductWithCategoryDTO(product);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Product not found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found");
        }
        repository.deleteById(id);
    }

    private void update(Product product, ProductWithCategoryDTO dto) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgUrl(dto.getImgUrl());
        product.getCategories().clear();
        for (CategoryDTO x : dto.getCategories()) {
            Category category = categoryRepository.getReferenceById(x.getId());
            product.getCategories().add(category);
        }
    }
}
