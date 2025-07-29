package com.ra.ss16baitap.controller;

import com.ra.ss16baitap.model.dto.request.OrderRequest;
import com.ra.ss16baitap.model.dto.request.OrderResponse;
import com.ra.ss16baitap.model.dto.response.APIResponse;
import com.ra.ss16baitap.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<APIResponse<?>> createOrder(@RequestBody OrderRequest request, Principal principal) {
        orderService.createOrder(request, principal.getName());
        return ResponseEntity.ok(new APIResponse<>("Đặt hàng thành công", true, null, 200));
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<APIResponse<?>> getMyOrders(Principal principal) {
        List<OrderResponse> orders = orderService.getMyOrders(principal.getName());
        return ResponseEntity.ok(new APIResponse<>("Lấy đơn hàng cá nhân thành công", true, orders, 200));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public ResponseEntity<APIResponse<?>> getAllOrders() {
        List<OrderResponse> orders = orderService.getAllOrders();
        return ResponseEntity.ok(new APIResponse<>("Lấy tất cả đơn hàng thành công", true, orders, 200));
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<APIResponse<?>> updateStatus(@PathVariable Long id, @RequestParam String status) {
        orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(new APIResponse<>("Cập nhật trạng thái thành công", true, null, 200));
    }
}
