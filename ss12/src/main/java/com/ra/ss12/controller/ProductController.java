package com.ra.ss12.controller;

import com.ra.ss12.model.entity.Product;
import com.ra.ss12.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/products")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @PostMapping
    public Product add(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
