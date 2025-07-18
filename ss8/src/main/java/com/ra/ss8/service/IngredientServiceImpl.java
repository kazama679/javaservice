package com.ra.ss8.service;

import com.ra.ss8.exception.ResourceNotFoundException;
import com.ra.ss8.model.entity.Ingredient;
import com.ra.ss8.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public Ingredient save(Ingredient ingredient, MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = cloudinaryService.uploadFile(imageFile);
            ingredient.setImage(imageUrl);
        }
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient update(Long id, Ingredient updatedIngredient, MultipartFile imageFile) {
        Ingredient existing = ingredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found"));

        existing.setName(updatedIngredient.getName());
        existing.setStock(updatedIngredient.getStock());
        existing.setExpiry(updatedIngredient.getExpiry());

        if (imageFile != null && !imageFile.isEmpty()) {
            String newImageUrl = cloudinaryService.uploadFile(imageFile);
            existing.setImage(newImageUrl);
        }

        return ingredientRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found"));
        ingredientRepository.delete(ingredient);
    }

    @Override
    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }
}
