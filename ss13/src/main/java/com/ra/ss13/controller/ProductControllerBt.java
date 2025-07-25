package com.ra.ss13.controller;

import com.ra.ss13.model.dto.response.APIResponse;
import com.ra.ss13.model.entity.Product;
import com.ra.ss13.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductControllerBt {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<APIResponse<List<Product>>> getAll() {
        return ResponseEntity.ok(new APIResponse<>(true, "Lấy danh sách sản phẩm thành công", productService.getAll(), HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Product>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new APIResponse<>(true, "Lấy sản phẩm theo ID thành công", productService.getById(id), HttpStatus.OK));
    }
}
