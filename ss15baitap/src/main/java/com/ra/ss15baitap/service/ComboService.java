package com.ra.ss15baitap.service;

import com.ra.ss15baitap.model.entity.Combo;

import java.util.List;

public interface ComboService {
    List<Combo> findAll();
    Combo save(Combo combo);
    Combo update(Long id, Combo combo);
    void delete(Long id);
}
