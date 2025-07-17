package com.ra.ss7.controller;

import com.ra.ss7.model.dto.DataResponse;
import com.ra.ss7.model.entity.PaymentSlip;
import com.ra.ss7.service.PaymentSlipService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paymentslips")
@RequiredArgsConstructor
public class PaymentSlipController {

    @Autowired
    private PaymentSlipService paymentSlipService;

    @GetMapping
    public ResponseEntity<DataResponse<List<PaymentSlip>>> getAllPaymentSlips() {
        List<PaymentSlip> list = paymentSlipService.getAllPaymentSlips();
        return ResponseEntity.ok(new DataResponse<>(list, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<PaymentSlip>> getPaymentSlipById(@PathVariable Long id) {
        PaymentSlip slip = paymentSlipService.getPaymentSlipById(id);
        if (slip == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
        }
        return ResponseEntity.ok(new DataResponse<>(slip, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<PaymentSlip>> addPaymentSlip(@RequestBody PaymentSlip paymentSlip) {
        PaymentSlip created = paymentSlipService.addPaymentSlip(paymentSlip);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new DataResponse<>(created, HttpStatus.CREATED));
    }
}