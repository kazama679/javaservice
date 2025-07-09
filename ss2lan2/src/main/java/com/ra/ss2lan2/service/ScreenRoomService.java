package com.ra.ss2lan2.service;

import com.ra.ss2lan2.model.entity.ScreenRoom;
import com.ra.ss2lan2.repository.ScreenRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScreenRoomService implements IService<ScreenRoom, Long> {
    @Autowired
    private ScreenRoomRepository screenRoomRepository;

    public List<ScreenRoom> findAll() {
        return screenRoomRepository.findAll();
    }

    @Override
    public ScreenRoom save(ScreenRoom entity) {
        return screenRoomRepository.save(entity);
    }

    @Override
    public Optional<ScreenRoom> findById(Long id) {
        return screenRoomRepository.findById(id);
    }

    @Override
    public ScreenRoom update(ScreenRoom entity) {
        return screenRoomRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        screenRoomRepository.deleteById(id);
    }
}
