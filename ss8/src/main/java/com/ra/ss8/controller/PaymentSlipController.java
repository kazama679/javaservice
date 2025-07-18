package com.ra.ss8.controller;

import com.ra.ss8.model.entity.PaymentSlip;
import com.ra.ss8.model.resonse.ApiResponse;
import com.ra.ss8.service.PaymentSlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paymentslips")
public class PaymentSlipController {

    @Autowired
    private PaymentSlipService service;

    @PostMapping
    public ApiResponse create(@RequestBody PaymentSlip slip) {
        return ApiResponse.success(service.create(slip));
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable Long id, @RequestBody PaymentSlip slip) {
        return ApiResponse.success(service.update(id, slip));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.success("Deleted successfully");
    }

    @GetMapping
    public ApiResponse getAll() {
        List<PaymentSlip> list = service.findAll();
        return ApiResponse.success(list);
    }
}
