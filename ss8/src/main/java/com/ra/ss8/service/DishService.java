package com.ra.ss8.service;

import com.ra.ss8.model.dto.DishDTO;
import com.ra.ss8.model.entity.Dish;

import java.util.List;

public interface DishService {
    Dish createDish(DishDTO dto);
    Dish updateDish(Long id, DishDTO dto);
    void deleteDish(Long id);
    List<Dish> getAllDishes();
}