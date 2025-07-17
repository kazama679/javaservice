package com.ra.ss7.service;

import com.ra.ss7.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;

@Service
public class StatisticalService {

    @Autowired
    private SeedRepository seedRepository;

    @Autowired
    private HarvestRepository harvestRepository;

    @Autowired
    private PaymentSlipRepository paymentSlipRepository;

    @Autowired
    private WorkerRepository workerRepository;

    public int countRemainingSeeds() {
        return seedRepository.findAll()
                .stream()
                .mapToInt(seed -> seed.getQuantity() != null ? seed.getQuantity() : 0)
                .sum();
    }

    public double totalHarvestMoneyThisMonth() {
        LocalDateTime start = YearMonth.now().atDay(1).atStartOfDay();
        LocalDateTime end = LocalDateTime.now();

        return harvestRepository.findAll()
                .stream()
                .filter(h -> h.getCreatedAt() != null && h.getCreatedAt().isAfter(start) && h.getCreatedAt().isBefore(end))
                .mapToDouble(h -> h.getTotalMoney() != null ? h.getTotalMoney() : 0)
                .sum();
    }

    public Map<String, Object> totalPaymentSlipsThisMonth() {
        LocalDateTime start = YearMonth.now().atDay(1).atStartOfDay();
        LocalDateTime end = LocalDateTime.now();

        List<Double> filtered = paymentSlipRepository.findAll()
                .stream()
                .filter(p -> p.getCreatedAt() != null && p.getCreatedAt().isAfter(start) && p.getCreatedAt().isBefore(end))
                .map(p -> p.getMoney() != null ? p.getMoney() : 0.0)
                .toList();

        Map<String, Object> result = new HashMap<>();
        result.put("count", filtered.size());
        result.put("total", filtered.stream().mapToDouble(Double::doubleValue).sum());
        return result;
    }

    public Map<String, Double> profitLossOverYear() {
        Map<String, Double> result = new LinkedHashMap<>();

        for (int month = 1; month <= 12; month++) {
            YearMonth ym = YearMonth.of(Year.now().getValue(), month);
            LocalDateTime start = ym.atDay(1).atStartOfDay();
            LocalDateTime end = ym.atEndOfMonth().atTime(LocalTime.MAX);

            double income = harvestRepository.findAll()
                    .stream()
                    .filter(h -> h.getCreatedAt() != null && !h.getCreatedAt().isBefore(start) && !h.getCreatedAt().isAfter(end))
                    .mapToDouble(h -> h.getTotalMoney() != null ? h.getTotalMoney() : 0)
                    .sum();

            double outcome = paymentSlipRepository.findAll()
                    .stream()
                    .filter(p -> p.getCreatedAt() != null && !p.getCreatedAt().isBefore(start) && !p.getCreatedAt().isAfter(end))
                    .mapToDouble(p -> p.getMoney() != null ? p.getMoney() : 0)
                    .sum();

            result.put("Tháng " + month, income - outcome);
        }
        return result;
    }

    public double totalWorkerSalary() {
        return workerRepository.findAll()
                .stream()
                .mapToDouble(w -> w.getSalary() != null ? w.getSalary() : 0)
                .sum();
    }
}
