package com.ra.ss6.controller;
import com.ra.ss6.model.entity.Classes;
import com.ra.ss6.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/classes")
public class ClassesController {
    @Autowired
    private ClassesService classesService;

    @GetMapping
    public List<Classes> getAll() {
        return classesService.getAll();
    }

    @PostMapping
    public Classes create(@RequestBody Classes classes) {
        return classesService.save(classes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classes> getById(@PathVariable Long id) {
        return classesService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classes> update(@PathVariable Long id, @RequestBody Classes updatedClass) {
        return classesService.findById(id)
                .map(existing -> {
                    existing.setClassName(updatedClass.getClassName());
                    existing.setStatus(updatedClass.getStatus());
                    return ResponseEntity.ok(classesService.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        classesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
