package com.ra.ss8.controller;

import com.ra.ss8.model.dto.ResponseObject;
import com.ra.ss8.model.entity.Ingredient;
import com.ra.ss8.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<ResponseObject> createIngredient(@ModelAttribute Ingredient ingredient,
                                                           @RequestParam("imageFile") MultipartFile imageFile) {
        Ingredient saved = ingredientService.save(ingredient, imageFile);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .status("success")
                        .message("Created successfully")
                        .data(saved)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateIngredient(@PathVariable Long id,
                                                           @ModelAttribute Ingredient ingredient,
                                                           @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        Ingredient updated = ingredientService.update(id, ingredient, imageFile);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .status("success")
                        .message("Updated successfully")
                        .data(updated)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteIngredient(@PathVariable Long id) {
        ingredientService.delete(id);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .status("success")
                        .message("Deleted successfully")
                        .data(null)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getAllIngredients() {
        List<Ingredient> ingredients = ingredientService.findAll();
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .status("success")
                        .message("Fetched successfully")
                        .data(ingredients)
                        .build()
        );
    }
}