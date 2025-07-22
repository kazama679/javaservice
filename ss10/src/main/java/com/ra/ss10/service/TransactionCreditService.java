package com.ra.ss10.service;

import com.ra.ss10.model.entity.TransactionCredit;

public interface TransactionCreditService {
    TransactionCredit createTransaction(TransactionCredit transaction);
    void sendMonthlyReport();
}