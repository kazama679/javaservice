package com.ra.ss15baitap.service.impl;

import com.ra.ss15baitap.repository.ComboOrderRepository;
import com.ra.ss15baitap.repository.TicketOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final TicketOrderRepository ticketOrderRepo;
    private final ComboOrderRepository comboOrderRepo;

    public BigDecimal getRevenue(String type, String value) {
        String format = getFormat(type);
        return ticketOrderRepo.getRevenueByTime(format, value);
    }

    public Long getAttendance(String type, String value) {
        String format = getFormat(type);
        return ticketOrderRepo.getTicketsSoldByTime(format, value);
    }

    public Long getComboUsage(String type, String value) {
        String format = getFormat(type);
        return comboOrderRepo.getComboUsedByTime(format, value);
    }

    private String getFormat(String type) {
        return switch (type.toLowerCase()) {
            case "day" -> "%Y-%m-%d";
            case "month" -> "%Y-%m";
            case "year" -> "%Y";
            default -> throw new IllegalArgumentException("Invalid type: " + type);
        };
    }
}
