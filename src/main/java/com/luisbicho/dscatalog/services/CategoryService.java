package com.luisbicho.dscatalog.services;

import com.luisbicho.dscatalog.dto.CategoryDTO;
import com.luisbicho.dscatalog.entities.Category;
import com.luisbicho.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public Page<CategoryDTO> searchAll(Pageable pageable) {
        Page<Category> result = repository.searchAll(pageable);
        return result.map(x -> new CategoryDTO(x));
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> result = repository.findById(id);
        return new CategoryDTO(result.get());
    }

}
