package com.ra.ss6.service;
import com.ra.ss6.model.entity.Classes;
import com.ra.ss6.repository.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassesService {
    @Autowired
    private ClassesRepository classesRepository;

    public List<Classes> getAll() {
        return classesRepository.findAll();
    }

    public Classes save(Classes classes) {
        return classesRepository.save(classes);
    }

    public Optional<Classes> findById(Long id) {
        return classesRepository.findById(id);
    }

    public void delete(Long id) {
        classesRepository.deleteById(id);
    }
}
