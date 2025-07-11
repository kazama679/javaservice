package com.ra.ss3lan2.service;

import com.ra.ss3lan2.model.entity.Category;
import com.ra.ss3lan2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(String id) {
        try {
            return categoryRepository.findById(id).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteCategoryById(String id) {
        try {
            if (categoryRepository.existsById(id)) {
                categoryRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveCategory(Category category) {
        try {
            if (!categoryRepository.existsById(category.getCateId())) {
                categoryRepository.save(category);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editCategory(Category category) {
        try {
            if (categoryRepository.existsById(category.getCateId())) {
                categoryRepository.save(category);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
