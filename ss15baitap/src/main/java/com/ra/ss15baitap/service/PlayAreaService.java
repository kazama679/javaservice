package com.ra.ss15baitap.service;

import com.ra.ss15baitap.model.entity.PlayArea;

import java.util.List;

public interface PlayAreaService {
    List<PlayArea> findAll();
    PlayArea save(PlayArea area);
    PlayArea update(Long id, PlayArea area);
    void delete(Long id);
}
