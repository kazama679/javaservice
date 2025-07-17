package com.ra.ss7.service;

import com.ra.ss7.model.entity.Order;
import com.ra.ss7.model.entity.OrderDetail;
import com.ra.ss7.model.entity.Product;
import com.ra.ss7.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng với ID = " + id));
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<OrderDetail> getOrderDetailsByOrderId(Long orderId) {
        Order order = getOrderById(orderId);
        return order.getOrderDetails();
    }
}
