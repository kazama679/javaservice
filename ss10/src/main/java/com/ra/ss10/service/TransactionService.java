package com.ra.ss10.service;

import com.ra.ss10.model.entity.Transaction;

public interface TransactionService {
    Transaction transfer(Transaction transaction);
}