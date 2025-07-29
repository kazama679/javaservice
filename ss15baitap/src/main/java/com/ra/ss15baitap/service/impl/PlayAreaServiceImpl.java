package com.ra.ss15baitap.service.impl;
import com.ra.ss15baitap.model.entity.PlayArea;
import com.ra.ss15baitap.repository.PlayAreaRepository;
import com.ra.ss15baitap.service.PlayAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayAreaServiceImpl implements PlayAreaService {
    private final PlayAreaRepository repository;

    @Override
    public List<PlayArea> findAll() {
        return repository.findAll();
    }

    @Override
    public PlayArea save(PlayArea area) {
        return repository.save(area);
    }

    @Override
    public PlayArea update(Long id, PlayArea area) {
        PlayArea existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khu vui chơi"));
        existing.setName(area.getName());
        existing.setDescription(area.getDescription());
        existing.setMaxCapacity(area.getMaxCapacity());
        existing.setStatus(area.getStatus());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
