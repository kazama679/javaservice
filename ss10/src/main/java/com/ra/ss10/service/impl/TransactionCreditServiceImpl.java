package com.ra.ss10.service.impl;

import com.ra.ss10.model.entity.Account;
import com.ra.ss10.model.entity.CreditCard;
import com.ra.ss10.model.entity.Notification;
import com.ra.ss10.model.entity.TransactionCredit;
import com.ra.ss10.repo.CreditCardRepository;
import com.ra.ss10.repo.NotificationRepository;
import com.ra.ss10.repo.TransactionCreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransactionCreditServiceImpl {

    @Autowired
    private TransactionCreditRepository transactionCreditRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    private static final Logger logger = LoggerFactory.getLogger(com.ra.ss10.service.TransactionCreditService.class);

    public TransactionCredit createTransaction(TransactionCredit transaction) {
        CreditCard card = transaction.getCreditCardSender();
        card = creditCardRepository.findById(card.getId())
                .orElseThrow(() -> new RuntimeException("Thẻ không tồn tại"));

        double available = card.getSpendingLimit() - card.getAmountSpent();

        if (transaction.getMoney() > available) {
            transaction.setStatus("thất bại");
            transaction.setId(UUID.randomUUID());
            transactionCreditRepository.save(transaction);
            logger.error("Giao dịch thất bại: vượt quá hạn mức chi tiêu");

            sendNotification(card.getAccount(), "Giao dịch thất bại: vượt quá hạn mức");
            return transaction;
        }

        card.setAmountSpent(card.getAmountSpent() + transaction.getMoney());
        creditCardRepository.save(card);

        transaction.setId(UUID.randomUUID());
        transaction.setStatus("thành công");
        transactionCreditRepository.save(transaction);

        sendNotification(card.getAccount(), "Đã chi tiêu: -" + transaction.getMoney());

        sendNotification(transaction.getAccountReceiver(), "Bạn đã nhận được khoản chi tiêu: +" + transaction.getMoney());

        return transaction;
    }

    private void sendNotification(Account account, String content) {
        Notification noti = new Notification();
        noti.setId(UUID.randomUUID());
        noti.setAccount(account);
        noti.setContent(content);
        noti.setStatus("chưa đọc");
        noti.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(noti);
    }
}
