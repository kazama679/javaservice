package com.ra.ss13.controller;

import com.ra.ss13.model.dto.response.APIResponse;
import com.ra.ss13.model.entity.ProductCart;
import com.ra.ss13.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<APIResponse<ProductCart>> addToCart(@RequestParam Long productId, @RequestParam int quantity, Principal principal) {
        ProductCart cart = cartService.addToCart(productId, quantity, principal.getName());
        return ResponseEntity.ok(new APIResponse<>(true, "Thêm vào giỏ hàng thành công", cart, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<ProductCart>>> getCart(Principal principal) {
        List<ProductCart> cartItems = cartService.getCart(principal.getName());
        return ResponseEntity.ok(new APIResponse<>(true, "Lấy giỏ hàng thành công", cartItems, HttpStatus.OK));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<APIResponse<String>> remove(@PathVariable Long id) {
        cartService.removeItem(id);
        return ResponseEntity.ok(new APIResponse<>(true, "Xóa khỏi giỏ hàng thành công", "Deleted", HttpStatus.OK));
    }
}
