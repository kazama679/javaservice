package com.ra.ss16baitap.service;

import java.math.BigDecimal;

public interface ReportService {
    BigDecimal getRevenue(String type, String value);
}