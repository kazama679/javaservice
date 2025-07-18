package com.ra.ss8.service;

import com.ra.ss8.model.entity.PaymentSlip;

import java.util.List;

public interface PaymentSlipService {
    PaymentSlip create(PaymentSlip paymentSlip);
    PaymentSlip update(Long id, PaymentSlip paymentSlip);
    void delete(Long id);
    List<PaymentSlip> findAll();
}
