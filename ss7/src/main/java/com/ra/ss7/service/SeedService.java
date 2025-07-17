package com.ra.ss7.service;

import com.ra.ss7.model.entity.Seed;
import com.ra.ss7.repository.SeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeedService {
    @Autowired
    private SeedRepository seedRepository;

    public List<Seed> getAllSeeds() {
        return seedRepository.findAll();
    }

    public Seed getSeedById(Long id) {
        return seedRepository.findById(id).orElse(null);
    }

    public Seed addSeed(Seed seed) {
        return seedRepository.save(seed);
    }

    public Seed updateSeed(Long id, Seed seed) {
        Optional<Seed> optionalSeed = seedRepository.findById(id);
        if (optionalSeed.isPresent()) {
            Seed existing = optionalSeed.get();
            existing.setSeedName(seed.getSeedName());
            existing.setQuantity(seed.getQuantity());
            return seedRepository.save(existing);
        }
        return null;
    }

    public void deleteSeed(Long id) {
        seedRepository.deleteById(id);
    }
}
