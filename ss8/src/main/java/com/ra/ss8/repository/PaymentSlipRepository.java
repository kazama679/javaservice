package com.ra.ss8.repository;

import com.ra.ss8.model.entity.PaymentSlip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentSlipRepository extends JpaRepository<PaymentSlip, Long> {
}
