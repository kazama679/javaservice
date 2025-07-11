package com.ra.ss3lan2.service;

import com.ra.ss3lan2.model.entity.Country;
import com.ra.ss3lan2.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepo;

    public List<Country> findAll() { return countryRepo.findAll(); }
    public Country save(Country c) { return countryRepo.save(c); }
    public Country findById(Long id) { return countryRepo.findById(id).orElse(null); }
    public void delete(Long id) { countryRepo.deleteById(id); }
    public List<Country> search(String keyword) {
        return countryRepo.findByCountryNameContainingIgnoreCase(keyword);
    }
}