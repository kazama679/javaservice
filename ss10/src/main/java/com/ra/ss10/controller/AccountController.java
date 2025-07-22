package com.ra.ss10.controller;

import com.ra.ss10.model.entity.Account;
import com.ra.ss10.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody Account account) {
        return ResponseEntity.status(201).body(accountService.create(account));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> update(@PathVariable UUID id, @RequestBody Account account) {
        account.setId(id);
        Account updated = accountService.update(account);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        accountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAll() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/cccd/{cccd}")
    public ResponseEntity<Account> findByCccd(@PathVariable String cccd) {
        return ResponseEntity.ok(accountService.findByCccd(cccd));
    }
}
