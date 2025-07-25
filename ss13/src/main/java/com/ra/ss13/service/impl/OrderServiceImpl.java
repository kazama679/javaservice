package com.ra.ss13.service.impl;

import com.ra.ss13.model.entity.Order;
import com.ra.ss13.model.entity.OrderDetail;
import com.ra.ss13.model.entity.ProductCart;
import com.ra.ss13.model.entity.UserBt;
import com.ra.ss13.repository.OrderDetailRepository;
import com.ra.ss13.repository.OrderRepository;
import com.ra.ss13.repository.ProductCartRepository;
import com.ra.ss13.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductCartRepository cartRepo;
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private OrderDetailRepository detailRepo;

    @Override
    public void checkout(UserBt user, String receiver, String phone, String address) {
        List<ProductCart> cartItems = cartRepo.findByUser(user);
        if (cartItems.isEmpty()) throw new RuntimeException("Cart is empty");

        double total = cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        Order order = Order.builder()
                .receiver(receiver)
                .phoneNumber(phone)
                .address(address)
                .totalMoney(total)
                .orderDate(LocalDateTime.now())
                .status("PENDING")
                .build();
        orderRepo.save(order);

        for (ProductCart item : cartItems) {
            OrderDetail detail = OrderDetail.builder()
                    .order(order)
                    .product(item.getProduct())
                    .quantity(item.getQuantity())
                    .priceBuy(item.getProduct().getPrice())
                    .build();
            detailRepo.save(detail);
        }

        cartRepo.deleteAll(cartItems);
    }
}
