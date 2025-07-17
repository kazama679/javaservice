package com.ra.ss7.controller;

import com.ra.ss7.model.dto.DataResponse;
import com.ra.ss7.model.entity.Order;
import com.ra.ss7.model.entity.OrderDetail;
import com.ra.ss7.model.entity.Product;
import com.ra.ss7.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Order>>> getAllOrders(){
        return ResponseEntity.ok(new DataResponse<>(orderService.getAllOrder(), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Order>> addOrders(@RequestBody Order order){
        return ResponseEntity.ok(new DataResponse<>(orderService.createOrder(order), HttpStatus.CREATED));
    }

    @GetMapping("{id}")
    public ResponseEntity<DataResponse<Order>> getProductById(Long id){
        return ResponseEntity.ok(new DataResponse<>(orderService.getOrderById(id), HttpStatus.OK));
    }

    @GetMapping("{id}/details")
    public ResponseEntity<DataResponse<List<OrderDetail>>> getOrderDetailsByOrderId(@PathVariable Long id) {
        List<OrderDetail> orderDetails = orderService.getOrderDetailsByOrderId(id);
        return ResponseEntity.ok(new DataResponse<>(orderDetails, HttpStatus.OK));
    }
}
