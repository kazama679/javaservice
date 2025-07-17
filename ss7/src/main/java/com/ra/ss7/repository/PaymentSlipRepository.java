package com.ra.ss7.repository;

import com.ra.ss7.model.entity.PaymentSlip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentSlipRepository extends JpaRepository<PaymentSlip, Long> {
}