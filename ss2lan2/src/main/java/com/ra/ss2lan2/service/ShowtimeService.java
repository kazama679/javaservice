package com.ra.ss2lan2.service;

import com.ra.ss2lan2.model.entity.Showtime;
import com.ra.ss2lan2.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowtimeService implements IService<Showtime, Long> {
    @Autowired
    private ShowtimeRepository showtimeRepository;

    public List<Showtime> findAll() {
        return showtimeRepository.findAll();
    }

    @Override
    public Showtime save(Showtime entity) {
        return showtimeRepository.save(entity);
    }

    @Override
    public Optional<Showtime> findById(Long id) {
        return showtimeRepository.findById(id);
    }

    @Override
    public Showtime update(Showtime entity) {
        return showtimeRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        showtimeRepository.deleteById(id);
    }
}
