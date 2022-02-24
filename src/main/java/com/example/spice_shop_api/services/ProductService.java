package com.example.spice_shop_api.services;

import com.example.spice_shop_api.models.Product;
import com.example.spice_shop_api.repositories.ProductRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<List<Product>> getAllProducts(String name) {
        try {
            List<Product> products = new ArrayList<Product>();
            if (name == null) {
                products.addAll(productRepository.findAll());
            }
            else {
                products.addAll(productRepository.findByNameContaining(name));
            }
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Product> getProductById(Long productId) {
        Optional<Product> productObject = productRepository.findById(productId);
        return productObject.map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Product> createProduct(Product productObject) {
        try {
            Product newProduct = productRepository
                    .save(new Product(productObject.getId(), productObject.getName(), productObject.getDescription(),
                            productObject.getPrice(), productObject.getImage(), productObject.getStock()));
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
