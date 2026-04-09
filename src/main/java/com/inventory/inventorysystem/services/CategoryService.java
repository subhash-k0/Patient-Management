package com.inventory.inventorysystem.services;

import com.inventory.inventorysystem.dtos.CategoryDTO;
import com.inventory.inventorysystem.dtos.Response;

public interface CategoryService {

    Response createCategory(CategoryDTO categoryDTO);

    Response getAllCategories();

    Response getCategoryById(Long id);

    Response updateCategory(Long id, CategoryDTO categoryDTO);

    Response deleteCategory(Long id);
}