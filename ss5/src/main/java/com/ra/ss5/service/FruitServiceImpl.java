package com.ra.ss5.service;

import com.ra.ss5.model.entity.FruitDTO;
import com.ra.ss5.model.entity.FruitProduct;
import com.ra.ss5.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FruitServiceImpl implements FruitService {
    @Autowired
    private FruitRepository fruitRepository;

    @Override
    public List<FruitDTO> getAllFruits() {
        return fruitRepository.findAll().stream()
                .map(fruit -> new FruitDTO(fruit.getId(), fruit.getName(), fruit.getPrice(), fruit.getStock()))
                .collect(Collectors.toList());
    }

    @Override
    public FruitProduct getFruitById(Long id) {
        return fruitRepository.findById(id).orElse(null);
    }

    @Override
    public FruitProduct createFruit(FruitProduct fruit) {
        return fruitRepository.save(fruit);
    }

    @Override
    public FruitProduct updateFruit(Long id, FruitProduct updatedFruit) {
        FruitProduct existing = getFruitById(id);
        if (existing != null) {
            existing.setName(updatedFruit.getName());
            existing.setPrice(updatedFruit.getPrice());
            existing.setStock(updatedFruit.getStock());
            existing.setStatus(updatedFruit.getStatus());
            return fruitRepository.save(existing);
        }
        return null;
    }

    @Override
    public void deleteFruit(Long id) {
        fruitRepository.deleteById(id);
    }
}