package com.ra.ss7.controller;

import com.ra.ss7.model.dto.DataResponse;
import com.ra.ss7.service.StatisticalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticalController {

    @Autowired
    private StatisticalService statisticalService;

    @GetMapping("/remaining-seeds")
    public ResponseEntity<DataResponse<Integer>> countRemainingSeeds() {
        int count = statisticalService.countRemainingSeeds();
        return ResponseEntity.ok(new DataResponse<>(count, HttpStatus.OK));
    }

    @GetMapping("/harvest-money")
    public ResponseEntity<DataResponse<Double>> totalHarvestMoneyThisMonth() {
        double money = statisticalService.totalHarvestMoneyThisMonth();
        return ResponseEntity.ok(new DataResponse<>(money, HttpStatus.OK));
    }

    @GetMapping("/payment-slips")
    public ResponseEntity<DataResponse<Map<String, Object>>> totalPaymentSlipsThisMonth() {
        Map<String, Object> data = statisticalService.totalPaymentSlipsThisMonth();
        return ResponseEntity.ok(new DataResponse<>(data, HttpStatus.OK));
    }

    @GetMapping("/profit-loss")
    public ResponseEntity<DataResponse<Map<String, Double>>> profitLossOverYear() {
        Map<String, Double> data = statisticalService.profitLossOverYear();
        return ResponseEntity.ok(new DataResponse<>(data, HttpStatus.OK));
    }

    @GetMapping("/worker-salary")
    public ResponseEntity<DataResponse<Double>> totalWorkerSalary() {
        double total = statisticalService.totalWorkerSalary();
        return ResponseEntity.ok(new DataResponse<>(total, HttpStatus.OK));
    }
}
