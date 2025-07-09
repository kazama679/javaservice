package com.ra.ss2lan2.service;

import com.ra.ss2lan2.model.entity.Seat;
import com.ra.ss2lan2.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeatService implements IService<Seat, Long> {
    @Autowired
    private SeatRepository seatRepository;

    @Override
    public Seat save(Seat entity) {
        return seatRepository.save(entity);
    }

    @Override
    public Optional<Seat> findById(Long id) {
        return seatRepository.findById(id);
    }

    @Override
    public Seat update(Seat entity) {
        return seatRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        seatRepository.deleteById(id);
    }
}
