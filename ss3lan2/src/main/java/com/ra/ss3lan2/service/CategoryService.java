package com.ra.ss3lan2.service;

import com.ra.ss3lan2.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(String id);
    boolean deleteCategoryById(String id);
    boolean saveCategory(Category category);
    boolean editCategory(Category category);
}
