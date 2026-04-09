package com.inventory.inventorysystem.repositories;

import com.inventory.inventorysystem.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
