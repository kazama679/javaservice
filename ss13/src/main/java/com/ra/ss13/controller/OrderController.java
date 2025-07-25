package com.ra.ss13.controller;

import com.ra.ss13.model.dto.request.CheckoutRequest;
import com.ra.ss13.model.dto.response.APIResponse;
import com.ra.ss13.model.entity.UserBt;
import com.ra.ss13.service.OrderService;
import com.ra.ss13.service.UserBtService;
import com.ra.ss13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserBtService userService;

    @PostMapping("/checkout")
    public ResponseEntity<APIResponse<String>> checkout(@RequestBody CheckoutRequest req, Principal principal) {
        UserBt user = userService.findByUsername(principal.getName());
        orderService.checkout(user, req.getReceiver(), req.getPhone(), req.getAddress());
        return ResponseEntity.ok(new APIResponse<>(true, "Đặt hàng thành công", "OK", HttpStatus.OK));
    }
}
