package com.example.spice_shop_api.services.impls;

import com.example.spice_shop_api.models.Category;
import com.example.spice_shop_api.models.Product;
import com.example.spice_shop_api.repositories.CategoryRepository;
import com.example.spice_shop_api.repositories.ProductRepository;
import com.example.spice_shop_api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<List<Product>> getAllProducts(String name) {
        try {
            List<Product> products = new ArrayList<>();
            if (name == null) {
                products.addAll(productRepository.findAll());
            } else {
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
                    .save(new Product(productObject));
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Product> updateProduct(Long productId, Product productChanges) {
        Optional<Product> productObject = productRepository.findById(productId);
        if (productObject.isPresent()) {
            Product productCurrent = productObject.get();

            productCurrent.setName(productChanges.getName());
            productCurrent.setDescription(productChanges.getDescription());
            productCurrent.setImage(productChanges.getImage());
            productCurrent.setPrice(productChanges.getPrice());
            productCurrent.setStock(productChanges.getStock());

            return new ResponseEntity<>(productRepository.save(productCurrent), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<Product> deleteProduct(Long productId) {
        try {
            productRepository.deleteById(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
