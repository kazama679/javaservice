package com.ra.ss12.service;

import com.ra.ss12.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    Product update(Long id, Product product);
    void delete(Long id);
}
