package com.ra.ss16baitap.service;

import com.ra.ss16baitap.model.dto.request.OrderRequest;
import com.ra.ss16baitap.model.dto.request.OrderResponse;
import com.ra.ss16baitap.model.dto.request.OrderItemRequest;
import com.ra.ss16baitap.model.entity.Order;
import com.ra.ss16baitap.model.entity.OrderItem;
import com.ra.ss16baitap.model.entity.Product;
import com.ra.ss16baitap.model.entity.User;
import com.ra.ss16baitap.repository.OrderRepository;
import com.ra.ss16baitap.repository.OrderItemRepository;
import com.ra.ss16baitap.repository.ProductRepository;
import com.ra.ss16baitap.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderResponse createOrder(OrderRequest request, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Order order = new Order();
        order.setUser(user);
        order.setCreatedDate(LocalDateTime.now());
        order.setStatus("PENDING");
        List<OrderItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItemRequest itemRequest : request.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemRequest.getQuantity());
            item.setPriceBuy(product.getPrice());
            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())));
            items.add(item);
        }
        order.setTotalMoney(total);
        Order savedOrder = orderRepository.save(order);
        for (OrderItem item : items) {
            item.setOrder(savedOrder);
            orderItemRepository.save(item);
        }
        return OrderResponse.fromOrder(savedOrder, items);
    }

    @Override
    public List<OrderResponse> getMyOrders(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Order> orders = orderRepository.findByUser(user);
        return orders.stream()
                .map(order -> {
                    List<OrderItem> items = orderItemRepository.findByOrder(order);
                    return OrderResponse.fromOrder(order, items);
                }).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> {
                    List<OrderItem> items = orderItemRepository.findByOrder(order);
                    return OrderResponse.fromOrder(order, items);
                }).collect(Collectors.toList());
    }

    @Override
    public void updateStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);
    }
}
