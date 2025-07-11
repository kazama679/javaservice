package com.ra.ss3lan2.service;

import com.ra.ss3lan2.model.entity.City;
import com.ra.ss3lan2.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public City findById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public void delete(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public List<City> searchByName(String name) {
        return cityRepository.findByCityNameContainingIgnoreCase(name);
    }
}