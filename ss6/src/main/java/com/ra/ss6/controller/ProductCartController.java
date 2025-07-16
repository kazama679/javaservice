package com.ra.ss6.controller;

import com.ra.ss6.model.entity.ProductCart;
import com.ra.ss6.model.entity.User;
import com.ra.ss6.service.ProductCartService;
import com.ra.ss6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class ProductCartController {
    @Autowired
    private ProductCartService productCartService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<ProductCart>> getCartItems(@RequestParam Long userId) {
        User user = userService.findById(userId);
        return ResponseEntity.ok(productCartService.getCartItemsByUser(user));
    }

    @PostMapping
    public ResponseEntity<ProductCart> addToCart(@RequestBody ProductCart cartItem) {
        return ResponseEntity.ok(productCartService.addToCart(cartItem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCart> updateQuantity(@PathVariable Long id, @RequestParam Integer quantity) {
        return ResponseEntity.ok(productCartService.updateQuantity(id, quantity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long id) {
        productCartService.removeFromCart(id);
        return ResponseEntity.noContent().build();
    }
}
