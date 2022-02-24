package com.example.spice_shop_api.repositories;

import com.example.spice_shop_api.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
