package com.ra.ss8.service;

import com.ra.ss8.model.dto.DishDTO;
import com.ra.ss8.model.entity.Dish;
import com.ra.ss8.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public Dish createDish(DishDTO dto) {
        String imageUrl = cloudinaryService.uploadImage(dto.getImage());
        Dish dish = Dish.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .status(dto.getStatus())
                .image(imageUrl)
                .build();

        return dishRepository.save(dish);
    }

    @Override
    public Dish updateDish(Long id, DishDTO dto) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dish not found"));
        dish.setName(dto.getName());
        dish.setDescription(dto.getDescription());
        dish.setPrice(dto.getPrice());
        dish.setStatus(dto.getStatus());
        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            String imageUrl = cloudinaryService.uploadImage(dto.getImage());
            dish.setImage(imageUrl);
        }
        return dishRepository.save(dish);
    }

    @Override
    public void deleteDish(Long id) {
        if (!dishRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dish not found");
        }
        dishRepository.deleteById(id);
    }

    @Override
    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }
}