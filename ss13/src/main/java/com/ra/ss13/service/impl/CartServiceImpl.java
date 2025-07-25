package com.ra.ss13.service.impl;

import com.ra.ss13.model.entity.ProductBt;
import com.ra.ss13.model.entity.ProductCart;
import com.ra.ss13.model.entity.UserBt;
import com.ra.ss13.repository.ProductCartRepository;
import com.ra.ss13.repository.ProductRepository;
import com.ra.ss13.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private ProductCartRepository cartRepo;
    @Autowired
    private ProductRepository productRepo;

    @Override
    public void addToCart(UserBt user, Long productId, int quantity) {
        ProductBt product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductCart cart = cartRepo.findByUserAndProduct_Id(user, productId)
                .orElse(new ProductCart(null, user, product, 0));
        cart.setQuantity(cart.getQuantity() + quantity);
        cartRepo.save(cart);
    }

    @Override
    public void removeFromCart(UserBt user, Long productId) {
        cartRepo.deleteByUserAndProduct_Id(user, productId);
    }

    @Override
    public void updateQuantity(UserBt user, Long productId, int quantity) {
        ProductCart cart = cartRepo.findByUserAndProduct_Id(user, productId)
                .orElseThrow(() -> new RuntimeException("Product not in cart"));
        cart.setQuantity(quantity);
        cartRepo.save(cart);
    }

    @Override
    public List<ProductCart> getUserCart(User user) {
        return cartRepo.findByUser(user);
    }
}
