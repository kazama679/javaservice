package com.ra.ss10.service.impl;

import com.ra.ss10.model.entity.CreditCard;
import com.ra.ss10.repo.CreditCardRepository;
import com.ra.ss10.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {
    private final CreditCardRepository creditCardRepository;

    @Override
    public CreditCard create(CreditCard creditCard) {
        creditCardRepository.findByAccount_Id(creditCard.getAccount().getId()).ifPresent(c -> {
            throw new RuntimeException("Tài khoản này đã có thẻ tín dụng!");
        });

        creditCard.setAmountSpent(0.0);
        creditCard.setStatus("active");

        return creditCardRepository.save(creditCard);
    }

    @Override
    public CreditCard updateLimit(UUID id, Double newLimit) {
        CreditCard card = creditCardRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy thẻ"));
        card.setSpendingLimit(newLimit);
        return creditCardRepository.save(card);
    }

    @Override
    public CreditCard updateStatus(UUID id, String status) {
        CreditCard card = creditCardRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy thẻ"));
        card.setStatus(status);
        return creditCardRepository.save(card);
    }
}
