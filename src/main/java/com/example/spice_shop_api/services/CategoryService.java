package com.example.spice_shop_api.services;

import com.example.spice_shop_api.models.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    ResponseEntity<List<Category>> getAllCategories(String name);
    ResponseEntity<Category> getCategoryById(Long categoryId);
    ResponseEntity<Category> createCategory(Category categoryObject);
    ResponseEntity<Category> updateCategory(Long categoryId, Category categoryChanges);
    ResponseEntity<Category> deleteCategory(Long categoryId);
    ResponseEntity<Category> addCategoryToProduct(Category categoryObject, Long productId);

}
