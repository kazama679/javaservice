package com.ra.ss2lan2.service;

import com.ra.ss2lan2.model.entity.Theater;
import com.ra.ss2lan2.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TheaterService implements IService<Theater, Long> {
    @Autowired
    private TheaterRepository theaterRepository;

    @Override
    public Theater save(Theater entity) {
        return theaterRepository.save(entity);
    }

    @Override
    public Optional<Theater> findById(Long id) {
        return theaterRepository.findById(id);
    }

    @Override
    public Theater update(Theater entity) {
        return theaterRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        theaterRepository.deleteById(id);
    }
}