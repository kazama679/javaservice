package com.ra.ss10.service;

import com.ra.ss10.model.entity.Account;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    Account create(Account account);
    Account update(Account account);
    Account findById(UUID id);
    Account findByCccd(String cccd);
    Boolean deleteById(UUID id);
    List<Account> findAll();
}
