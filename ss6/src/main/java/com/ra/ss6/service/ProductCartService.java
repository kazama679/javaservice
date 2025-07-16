package com.ra.ss6.service;

import com.ra.ss6.model.entity.ProductCart;
import com.ra.ss6.model.entity.User;
import com.ra.ss6.repository.ProductCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCartService {
    @Autowired
    private ProductCartRepository productCartRepository;

    public List<ProductCart> getCartItemsByUser(User user) {
        return productCartRepository.findByUser(user);
    }

    public ProductCart addToCart(ProductCart productCart) {
        return productCartRepository.save(productCart);
    }

    public ProductCart updateQuantity(Long id, Integer quantity) {
        ProductCart cartItem = productCartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cartItem.setQuantity(quantity);
        return productCartRepository.save(cartItem);
    }

    public void removeFromCart(Long id) {
        productCartRepository.deleteById(id);
    }
}
