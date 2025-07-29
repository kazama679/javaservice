package com.ra.ss15baitap.service.impl;

import com.ra.ss15baitap.model.entity.Combo;
import com.ra.ss15baitap.repository.ComboRepository;
import com.ra.ss15baitap.service.ComboService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComboServiceImpl implements ComboService {
    private final ComboRepository comboRepository;

    @Override
    public List<Combo> findAll() {
        return comboRepository.findAll();
    }

    @Override
    public Combo save(Combo combo) {
        return comboRepository.save(combo);
    }

    @Override
    public Combo update(Long id, Combo combo) {
        Combo existing = comboRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy combo"));
        existing.setName(combo.getName());
        existing.setDescription(combo.getDescription());
        existing.setPrice(combo.getPrice());
        existing.setItems(combo.getItems());
        existing.setStatus(combo.getStatus());
        return comboRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        comboRepository.deleteById(id);
    }
}
