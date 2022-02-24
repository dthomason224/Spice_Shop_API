package com.example.spice_shop_api.repositories;

import com.example.spice_shop_api.models.Category;
import com.example.spice_shop_api.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByNameContaining(String name);
}
