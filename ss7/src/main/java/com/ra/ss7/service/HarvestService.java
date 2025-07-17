package com.ra.ss7.service;

import com.ra.ss7.model.entity.Harvest;
import com.ra.ss7.repository.HarvestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HarvestService {
    @Autowired
    private HarvestRepository harvestRepository;

    public List<Harvest> getAllHarvests() {
        return harvestRepository.findAll();
    }

    public Harvest getHarvestById(Long id) {
        return harvestRepository.findById(id).orElse(null);
    }

    public Harvest addHarvest(Harvest harvest) {
        if (harvest.getCreatedAt() == null) {
            harvest.setCreatedAt(java.time.LocalDateTime.now());
        }
        return harvestRepository.save(harvest);
    }
}
