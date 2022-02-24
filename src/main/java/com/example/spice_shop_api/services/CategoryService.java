package com.example.spice_shop_api.services;

import com.example.spice_shop_api.models.Category;
import com.example.spice_shop_api.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<List<Category>> getAllCategories(String name) {
        try {
            List<Category> products = new ArrayList<>();
            if (name != null) {
                products.addAll(categoryRepository.findByNameContaining(name));
            } else {
                products.addAll(categoryRepository.findAll());
            }
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Category> getCategoryById(Long categoryId) {
        Optional<Category> categoryObject = categoryRepository.findById(categoryId);
        return categoryObject.map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

    }

    public ResponseEntity<Category> createCategory(Category categoryObject) {
        try {
            Category newCategory = categoryRepository
                    .save(new Category(categoryObject.getId(), categoryObject.getName(), categoryObject.getDescription()));
            return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Category> updateCategory(Long productId, Category categoryChanges) {
        Optional<Category> categoryObject = categoryRepository.findById(productId);
        if (categoryObject.isPresent()) {
            Category categoryCurrent = categoryObject.get();

            categoryCurrent.setName(categoryChanges.getName());
            categoryCurrent.setDescription(categoryChanges.getDescription());

            return new ResponseEntity<>(categoryRepository.save(categoryCurrent), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<Category> deleteCategory(Long categoryId) {
        try {
            categoryRepository.deleteById(categoryId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
