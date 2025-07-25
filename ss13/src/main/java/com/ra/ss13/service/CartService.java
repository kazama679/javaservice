package com.ra.ss13.service;

import com.ra.ss13.model.entity.ProductCart;
import com.ra.ss13.model.entity.UserBt;

import java.util.List;

public interface CartService {
    void addToCart(UserBt user, Long productId, int quantity);
    void removeFromCart(UserBt user, Long productId);
    void updateQuantity(UserBt user, Long productId, int quantity);
    List<ProductCart> getUserCart(UserBt user);
}
