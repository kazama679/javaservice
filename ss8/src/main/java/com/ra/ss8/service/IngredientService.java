package com.ra.ss8.service;

import com.ra.ss8.model.entity.Ingredient;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IngredientService {
    Ingredient save(Ingredient ingredient, MultipartFile imageFile);
    Ingredient update(Long id, Ingredient ingredient, MultipartFile imageFile);
    void delete(Long id);
    List<Ingredient> findAll();
}