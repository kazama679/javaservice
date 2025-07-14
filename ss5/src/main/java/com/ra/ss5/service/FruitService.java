package com.ra.ss5.service;

import com.ra.ss5.model.entity.FruitDTO;
import com.ra.ss5.model.entity.FruitProduct;

import java.util.List;

public interface FruitService {
    List<FruitDTO> getAllFruits();
    FruitProduct getFruitById(Long id);
    FruitProduct createFruit(FruitProduct fruit);
    FruitProduct updateFruit(Long id, FruitProduct fruit);
    void deleteFruit(Long id);
}
