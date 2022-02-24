package com.example.spice_shop_api.repositories;

import com.example.spice_shop_api.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
