package com.ra.ss14.model.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookTicketRequest {
    private Long showtimeId;
    private String seatNumber;
    private BigDecimal price;
}
