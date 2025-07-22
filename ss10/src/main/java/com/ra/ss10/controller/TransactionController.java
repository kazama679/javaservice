package com.ra.ss10.controller;

import com.ra.ss10.model.entity.Transaction;
import com.ra.ss10.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> transfer(@RequestBody Transaction transaction) {
        Transaction result = transactionService.transfer(transaction);
        return ResponseEntity.status(201).body(result);
    }
}
