package com.ra.ss7.controller;

import com.ra.ss7.model.dto.DataResponse;
import com.ra.ss7.model.entity.Seed;
import com.ra.ss7.service.SeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seeds")
@RequiredArgsConstructor
public class SeedController {

    @Autowired
    private SeedService seedService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Seed>>> getAllSeeds() {
        List<Seed> seeds = seedService.getAllSeeds();
        return ResponseEntity.ok(new DataResponse<>(seeds, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Seed>> getSeedById(@PathVariable Long id) {
        Seed seed = seedService.getSeedById(id);
        if (seed == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
        }
        return ResponseEntity.ok(new DataResponse<>(seed, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Seed>> addSeed(@RequestBody Seed seed) {
        Seed created = seedService.addSeed(seed);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new DataResponse<>(created, HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Seed>> updateSeed(@PathVariable Long id, @RequestBody Seed seed) {
        Seed updated = seedService.updateSeed(id, seed);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
        }
        return ResponseEntity.ok(new DataResponse<>(updated, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<String>> deleteSeed(@PathVariable Long id) {
        seedService.deleteSeed(id);
        return ResponseEntity.ok(new DataResponse<>("Xoá thành công!", HttpStatus.OK));
    }
}
