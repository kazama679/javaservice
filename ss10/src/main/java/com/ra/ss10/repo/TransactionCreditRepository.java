package com.ra.ss10.repo;

import com.ra.ss10.model.entity.TransactionCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionCreditRepository extends JpaRepository<TransactionCredit, UUID> {
    List<TransactionCredit> findByCreditCardSender_IdAndCreatedAtBetween(UUID cardId, LocalDateTime start, LocalDateTime end);
}