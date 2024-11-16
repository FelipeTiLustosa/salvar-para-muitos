package com.devsuperior.aula.services;

import com.devsuperior.aula.dto.CategoryDTO;
import com.devsuperior.aula.dto.ProductDTO;
import com.devsuperior.aula.entities.Category;
import com.devsuperior.aula.entities.Product;
import com.devsuperior.aula.repositories.CategoryRepository;
import com.devsuperior.aula.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDTO insert (ProductDTO dto) {
        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());

        for (CategoryDTO cat : dto.getCategories()) {
            Category category = new Category();
            category = categoryRepository.findById(cat.getId()).get();
            //category.setId(cat.getId());
            //category.setName(cat.getName());
            entity.getCategories().add(category);
        }
        entity = repository.save(entity);

        return new ProductDTO(entity);
    }
}
