package com.ra.ss16baitap.model.dto.request;

import com.ra.ss16baitap.model.dto.response.OrderItemResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private String username;
    private LocalDateTime createdDate;
    private String status;
    private BigDecimal totalMoney;
    private List<OrderItemResponse> items;
}
