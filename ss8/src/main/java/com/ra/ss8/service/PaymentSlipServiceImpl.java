package com.ra.ss8.service;

import com.ra.ss8.exception.ResourceNotFoundException;
import com.ra.ss8.model.entity.PaymentSlip;
import com.ra.ss8.repository.PaymentSlipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentSlipServiceImpl implements PaymentSlipService {
    @Autowired
    private PaymentSlipRepository repository;

    @Override
    public PaymentSlip create(PaymentSlip paymentSlip) {
        paymentSlip.setCreatedAt(LocalDateTime.now());
        return repository.save(paymentSlip);
    }

    @Override
    public PaymentSlip update(Long id, PaymentSlip updated) {
        PaymentSlip existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PaymentSlip not found with id: " + id));
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setMoney(updated.getMoney());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        PaymentSlip slip = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PaymentSlip not found with id: " + id));
        repository.delete(slip);
    }

    @Override
    public List<PaymentSlip> findAll() {
        return repository.findAll();
    }
}