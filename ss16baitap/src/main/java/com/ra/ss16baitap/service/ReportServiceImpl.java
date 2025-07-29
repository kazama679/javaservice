package com.ra.ss16baitap.service;

import com.ra.ss16baitap.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final OrderRepository orderRepo;

    @Override
    public BigDecimal getRevenue(String type, String value) {
        switch (type) {
            case "day":
                return orderRepo.getRevenueByDay(LocalDate.parse(value));
            case "month":
                return orderRepo.getRevenueByMonth(YearMonth.parse(value));
            case "year":
                return orderRepo.getRevenueByYear(Integer.parseInt(value));
            default:
                throw new IllegalArgumentException("Loại thống kê không hợp lệ");
        }
    }
}
