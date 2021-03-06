package com.example.spice_shop_api.services.impls;

import com.example.spice_shop_api.models.Category;
import com.example.spice_shop_api.models.Product;
import com.example.spice_shop_api.repositories.CategoryRepository;
import com.example.spice_shop_api.repositories.ProductRepository;
import com.example.spice_shop_api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategories(String name) {
        try {
            List<Category> categories = new ArrayList<>();
            if (name != null) {
                categories.addAll(categoryRepository.findByNameContaining(name));
            } else {
                categories.addAll(categoryRepository.findAll());
            }
            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<Category> getCategoryById(Long categoryId) {
        Optional<Category> categoryObject = categoryRepository.findById(categoryId);
        return categoryObject.map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

    }

    @Override
    public ResponseEntity<Category> createCategory(Category categoryObject) {
        try {
            Category newCategory = categoryRepository
                    .save(new Category(categoryObject));
            return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<Category> updateCategory(Long categoryId, Category categoryChanges) {
        Optional<Category> categoryObject = categoryRepository.findById(categoryId);
        if (categoryObject.isPresent()) {
            Category categoryCurrent = categoryObject.get();

            categoryCurrent.setName(categoryChanges.getName());
            categoryCurrent.setDescription(categoryChanges.getDescription());

            return new ResponseEntity<>(categoryRepository.save(categoryCurrent), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<Category> deleteCategory(Long categoryId) {
        try {
            categoryRepository.deleteById(categoryId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<List<Product>> getProductsByCategory(String name) {
        try {
            List<Product> products = productRepository.findAllByCategoryName(name);

            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
