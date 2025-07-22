package com.ra.ss10.service.impl;

import com.ra.ss10.model.entity.Account;
import com.ra.ss10.model.entity.Status;
import com.ra.ss10.repo.AccountRepository;
import com.ra.ss10.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account create(Account account) {
        account.setStatus(Status.ACTIVE);
        return accountRepository.save(account);
    }

    @Override
    public Account update(Account account) {
        Account existing = accountRepository.findById(account.getId()).orElse(null);
        if (existing == null) {
            return null;
        }
        logger.info("Thông tin cũ: {}", existing);
        logger.info("Thông tin mới: {}", account);

        existing.setFullname(account.getFullname());
        existing.setPhone(account.getPhone());
        existing.setCccd(account.getCccd());
        existing.setEmail(account.getEmail());
        existing.setMoney(account.getMoney());
        existing.setStatus(account.getStatus());
        return accountRepository.save(existing);
    }

    @Override
    public Account findById(UUID id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account findByCccd(String cccd) {
        return accountRepository.findByCccd(cccd).orElse(null);
    }

    @Override
    public Boolean deleteById(UUID id) {
        Account acc = accountRepository.findById(id).orElse(null);
        if (acc == null) {
            return false;
        }
        acc.setStatus(Status.INACTIVE);
        accountRepository.save(acc);
        return true;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }
}
