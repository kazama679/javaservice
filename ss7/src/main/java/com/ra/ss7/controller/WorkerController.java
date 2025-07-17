package com.ra.ss7.controller;

import com.ra.ss7.model.dto.DataResponse;
import com.ra.ss7.model.entity.Worker;
import com.ra.ss7.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workers")
@RequiredArgsConstructor
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Worker>>> getAllWorkers() {
        List<Worker> list = workerService.getAllWorkers();
        return ResponseEntity.ok(new DataResponse<>(list, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Worker>> getWorkerById(@PathVariable Long id) {
        Worker worker = workerService.getWorkerById(id);
        if (worker == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
        }
        return ResponseEntity.ok(new DataResponse<>(worker, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Worker>> addWorker(@RequestBody Worker worker) {
        Worker created = workerService.addWorker(worker);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new DataResponse<>(created, HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Worker>> updateWorker(@PathVariable Long id, @RequestBody Worker worker) {
        Worker updated = workerService.updateWorker(id, worker);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
        }
        return ResponseEntity.ok(new DataResponse<>(updated, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<String>> deleteWorker(@PathVariable Long id) {
        workerService.deleteWorker(id);
        return ResponseEntity.ok(new DataResponse<>("Xóa công nhân thành công!", HttpStatus.OK));
    }
}
