package com.ra.ss10.controller;

import com.ra.ss10.model.entity.TransactionCredit;
import com.ra.ss10.service.TransactionCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit-transactions")
public class TransactionCreditController {
    @Autowired
    private TransactionCreditService transactionCreditService;

    @PostMapping
    public ResponseEntity<TransactionCredit> create(@RequestBody TransactionCredit transaction) {
        TransactionCredit created = transactionCreditService.createTransaction(transaction);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
