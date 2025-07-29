package com.ra.ss16baitap.model.dto.response;

import com.ra.ss16baitap.model.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponse {
    private Long id;
    private String productName;
    private BigDecimal price;
    private int quantity;

    public static OrderItemResponse fromOrderItem(OrderItem item) {
        return OrderItemResponse.builder()
                .id(item.getId())
                .productName(item.getProduct().getName())
                .price(item.getPriceBuy())
                .quantity(item.getQuantity())
                .build();
    }
}