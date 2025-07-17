package com.ra.ss7.service;

import com.ra.ss7.model.entity.PaymentSlip;
import com.ra.ss7.repository.PaymentSlipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentSlipService {
    @Autowired
    private PaymentSlipRepository paymentSlipRepository;

    public List<PaymentSlip> getAllPaymentSlips() {
        return paymentSlipRepository.findAll();
    }

    public PaymentSlip getPaymentSlipById(Long id) {
        return paymentSlipRepository.findById(id).orElse(null);
    }

    public PaymentSlip addPaymentSlip(PaymentSlip paymentSlip) {
        if (paymentSlip.getCreatedAt() == null) {
            paymentSlip.setCreatedAt(LocalDateTime.now());
        }
        return paymentSlipRepository.save(paymentSlip);
    }
}