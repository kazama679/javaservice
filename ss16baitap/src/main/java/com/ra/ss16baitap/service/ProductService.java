package com.ra.ss16baitap.service;

import com.ra.ss16baitap.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product save(Product product);
    Product update(Long id, Product product);
    void delete(Long id);
}
