package com.ra.ss15baitap.controller;

import com.ra.ss15baitap.model.dto.response.APIResponse;
import com.ra.ss15baitap.model.entity.TicketOrder;
import com.ra.ss15baitap.service.impl.TicketOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket-orders")
public class TicketOrderController {
    @Autowired
    private TicketOrderService ticketOrderService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<APIResponse<TicketOrder>> placeOrder(
            @RequestParam Integer quantity,
            @RequestParam List<Long> comboIds
    ) {
        TicketOrder order = ticketOrderService.placeOrder(quantity, comboIds);
        return ResponseEntity.ok(new APIResponse<>("Đặt vé thành công", true, order, 200));
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<APIResponse<List<TicketOrder>>> getMyOrders() {
        List<TicketOrder> orders = ticketOrderService.getMyOrders();
        return ResponseEntity.ok(new APIResponse<>("Lịch sử đặt vé", true, orders, 200));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<APIResponse<List<TicketOrder>>> getAllOrders() {
        List<TicketOrder> orders = ticketOrderService.getAllOrders();
        return ResponseEntity.ok(new APIResponse<>("Tất cả đơn đặt vé", true, orders, 200));
    }
}
