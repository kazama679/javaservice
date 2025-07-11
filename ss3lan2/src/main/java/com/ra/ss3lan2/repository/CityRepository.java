package com.ra.ss3lan2.repository;

import com.ra.ss3lan2.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByCityNameContainingIgnoreCase(String keyword);
}