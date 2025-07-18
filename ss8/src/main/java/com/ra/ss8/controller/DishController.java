package com.ra.ss8.controller;

import com.ra.ss8.model.dto.DishDTO;
import com.ra.ss8.model.entity.Dish;
import com.ra.ss8.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @PostMapping
    public ResponseEntity<Dish> createDish(@ModelAttribute DishDTO dishDTO) {
        return ResponseEntity.ok(dishService.createDish(dishDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dish> updateDish(@PathVariable Long id, @ModelAttribute DishDTO dishDTO) {
        return ResponseEntity.ok(dishService.updateDish(id, dishDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Dish>> getAllDishes() {
        return ResponseEntity.ok(dishService.getAllDishes());
    }
}