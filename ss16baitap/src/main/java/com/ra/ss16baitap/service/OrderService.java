package com.ra.ss16baitap.service;

import com.ra.ss16baitap.model.dto.request.OrderRequest;
import com.ra.ss16baitap.model.dto.request.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest request, String username);
    List<OrderResponse> getMyOrders(String username);
    List<OrderResponse> getAllOrders();
    void updateStatus(Long orderId, String status);
}