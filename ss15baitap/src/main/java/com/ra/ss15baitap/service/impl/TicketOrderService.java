package com.ra.ss15baitap.service.impl;

import com.ra.ss15baitap.model.entity.*;
import com.ra.ss15baitap.repository.ComboRepository;
import com.ra.ss15baitap.repository.TicketOrderRepository;
import com.ra.ss15baitap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketOrderService {
    @Autowired
    private TicketOrderRepository ticketOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ComboRepository comboRepository;

    public TicketOrder placeOrder(Integer quantity, List<Long> comboIds) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        List<Combo> selectedCombos = comboRepository.findAllById(comboIds);
        BigDecimal totalComboPrice = selectedCombos.stream()
                .map(Combo::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal ticketPrice = BigDecimal.valueOf(200000);
        BigDecimal total = ticketPrice.multiply(BigDecimal.valueOf(quantity)).add(totalComboPrice);

        TicketOrder order = TicketOrder.builder()
                .user(user)
                .quantityTicket(quantity)
                .combos(selectedCombos)
                .totalMoney(total)
                .createdAt(LocalDateTime.now())
                .build();
        return ticketOrderRepository.save(order);
    }

    public List<TicketOrder> getMyOrders() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        return ticketOrderRepository.findByUser(user);
    }

    public List<TicketOrder> getAllOrders() {
        return ticketOrderRepository.findAll();
    }
}
