package com.example.spice_shop_api.controllers;

import com.example.spice_shop_api.models.Category;
import com.example.spice_shop_api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories(@RequestParam(required = false) String name) {
        return categoryService.getAllCategories(name);
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable(value = "categoryId") Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping("/categories/")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Category updatedCategory) {
        return categoryService.updateCategory(categoryId, updatedCategory);
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<Category> deleteCategory(@PathVariable(value = "categoryId") Long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
}
