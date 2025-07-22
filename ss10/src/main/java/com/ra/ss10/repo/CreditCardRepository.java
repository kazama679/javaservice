package com.ra.ss10.repo;

import com.ra.ss10.model.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CreditCardRepository extends JpaRepository<CreditCard, UUID> {
    Optional<CreditCard> findByAccount_Id(UUID accountId);
}
