package com.example.spice_shop_api.services;

import com.example.spice_shop_api.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<List<Product>> getAllProducts(String name);
    ResponseEntity<Product> getProductById(Long productId);
    ResponseEntity<Product> createProduct(Product productObject);
    ResponseEntity<Product> updateProduct(Long productId, Product productObject);
    ResponseEntity<Product> deleteProduct(Long productId);
}
