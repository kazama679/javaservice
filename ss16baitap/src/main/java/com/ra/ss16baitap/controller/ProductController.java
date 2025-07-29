package com.ra.ss16baitap.controller;

import com.ra.ss16baitap.model.dto.response.APIResponse;
import com.ra.ss16baitap.model.entity.Product;
import com.ra.ss16baitap.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<APIResponse<List<Product>>> getAll() {
        List<Product> list = productService.findAll();
        return ResponseEntity.ok(new APIResponse<>("Lấy danh sách sản phẩm thành công", true, list, 200));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public ResponseEntity<APIResponse<Product>> create(@RequestBody Product product) {
        Product saved = productService.save(product);
        return ResponseEntity.ok(new APIResponse<>("Thêm sản phẩm thành công", true, saved, 200));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public ResponseEntity<APIResponse<Product>> update(@PathVariable Long id, @RequestBody Product product) {
        Product updated = productService.update(id, product);
        return ResponseEntity.ok(new APIResponse<>("Cập nhật thành công", true, updated, 200));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public ResponseEntity<APIResponse<String>> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok(new APIResponse<>("Xóa sản phẩm thành công", true, null, 200));
    }
}
