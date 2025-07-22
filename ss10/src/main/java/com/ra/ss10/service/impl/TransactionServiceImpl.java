package com.ra.ss10.service.impl;

import com.ra.ss10.model.entity.*;
import com.ra.ss10.repo.AccountRepository;
import com.ra.ss10.repo.NotificationRepository;
import com.ra.ss10.repo.TransactionRepository;
import com.ra.ss10.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final NotificationRepository notificationRepository;

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Override
    public Transaction transfer(Transaction transaction) {
        Account sender = accountRepository.findById(transaction.getSender().getId()).orElse(null);
        Account receiver = accountRepository.findById(transaction.getReceiver().getId()).orElse(null);

        if (sender == null || receiver == null || sender.getMoney() < transaction.getMoney()) {
            transaction.setStatus("thất bại");
            transaction.setCreatedAt(LocalDateTime.now());
            transactionRepository.save(transaction);
            logger.error("Giao dịch thất bại: {}", transaction);
            return transaction;
        }

        sender.setMoney(sender.getMoney() - transaction.getMoney());
        receiver.setMoney(receiver.getMoney() + transaction.getMoney());
        accountRepository.save(sender);
        accountRepository.save(receiver);

        transaction.setStatus("thành công");
        transaction.setCreatedAt(LocalDateTime.now());
        transactionRepository.save(transaction);

        notificationRepository.save(Notification.builder()
                .account(sender)
                .content("Bạn đã chuyển " + transaction.getMoney() + " VNĐ. Số dư hiện tại: " + sender.getMoney())
                .status("chưa đọc")
                .createdAt(LocalDateTime.now())
                .build());

        notificationRepository.save(Notification.builder()
                .account(receiver)
                .content("Bạn đã nhận được " + transaction.getMoney() + " VNĐ. Số dư hiện tại: " + receiver.getMoney())
                .status("chưa đọc")
                .createdAt(LocalDateTime.now())
                .build());

        return transaction;
    }
}
