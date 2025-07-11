package com.ra.ss3lan2.service;

import com.ra.ss3lan2.model.entity.City;

import java.util.List;

public interface CityService {
    List<City> getAll();
    City findById(Long id);
    City save(City city);
    void delete(Long id);
    List<City> searchByName(String name);
}