package com.ra.ss7.controller;

import com.ra.ss7.model.dto.DataResponse;
import com.ra.ss7.model.entity.Harvest;
import com.ra.ss7.service.HarvestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/harvests")
@RequiredArgsConstructor
public class HarvestController {

    @Autowired
    private HarvestService harvestService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Harvest>>> getAllHarvests() {
        List<Harvest> list = harvestService.getAllHarvests();
        return ResponseEntity.ok(new DataResponse<>(list, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Harvest>> getHarvestById(@PathVariable Long id) {
        Harvest harvest = harvestService.getHarvestById(id);
        if (harvest == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
        }
        return ResponseEntity.ok(new DataResponse<>(harvest, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Harvest>> addHarvest(@RequestBody Harvest harvest) {
        Harvest created = harvestService.addHarvest(harvest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new DataResponse<>(created, HttpStatus.CREATED));
    }
}
