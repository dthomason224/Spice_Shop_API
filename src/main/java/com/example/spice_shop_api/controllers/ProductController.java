package com.example.spice_shop_api.controllers;

import com.example.spice_shop_api.models.Product;
import com.example.spice_shop_api.repositories.ProductRepository;
import com.example.spice_shop_api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String name) {
        return productService.getAllProducts(name);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable(value = "productId") Long productId) {
        return productService.getProductById(productId);
    }

    @PostMapping("/products/")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

//    @PutMapping("/products/{productId}")
//    public ResponseEntity<Product> updateProduct(@PathVariable(value = "productId") Long productId, @RequestBody Product newProduct) {
//
//    }
//
//    @DeleteMapping("/products/{productId}")
//    public void deleteCategory(@PathVariable(value = "productId") Long productId) {
//
//    }


}
