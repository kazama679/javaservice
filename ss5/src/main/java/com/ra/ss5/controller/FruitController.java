package com.ra.ss5.controller;

import com.ra.ss5.model.entity.FruitDTO;
import com.ra.ss5.model.entity.FruitProduct;
import com.ra.ss5.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fruits")
public class FruitController {
    @Autowired
    private FruitService fruitService;

    @GetMapping
    public List<FruitDTO> getAllFruits() {
        return fruitService.getAllFruits();
    }

    @GetMapping("/{id}")
    public FruitProduct getFruitById(@PathVariable Long id) {
        return fruitService.getFruitById(id);
    }

    @PostMapping
    public FruitProduct createFruit(@RequestBody FruitProduct fruit) {
        return fruitService.createFruit(fruit);
    }

    @PutMapping("/{id}")
    public FruitProduct updateFruit(@PathVariable Long id, @RequestBody FruitProduct fruit) {
        return fruitService.updateFruit(id, fruit);
    }

    @DeleteMapping("/{id}")
    public void deleteFruit(@PathVariable Long id) {
        fruitService.deleteFruit(id);
    }
}