package com.ra.ss16baitap.model.dto.response;

import com.ra.ss16baitap.model.entity.Order;
import com.ra.ss16baitap.model.entity.OrderItem;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private String username;
    private LocalDateTime createdDate;
    private String status;
    private BigDecimal totalMoney;
    private List<OrderItemResponse> items;

    public static OrderResponse fromOrder(Order order, List<OrderItem> items) {
        return OrderResponse.builder()
                .id(order.getId())
                .username(order.getUser() != null ? order.getUser().getEmail() : null)
                .createdDate(order.getCreatedDate())
                .status(order.getStatus())
                .totalMoney(order.getTotalMoney())
                .items(items.stream().map(OrderItemResponse::fromOrderItem).collect(Collectors.toList()))
                .build();
    }
}
