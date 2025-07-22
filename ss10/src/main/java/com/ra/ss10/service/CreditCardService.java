package com.ra.ss10.service;

import com.ra.ss10.model.entity.CreditCard;

import java.util.UUID;

public interface CreditCardService {
    CreditCard create(CreditCard creditCard);
    CreditCard updateLimit(UUID id, Double newLimit);
    CreditCard updateStatus(UUID id, String status);
}
