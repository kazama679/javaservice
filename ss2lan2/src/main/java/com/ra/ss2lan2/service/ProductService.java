package com.ra.ss2lan2.service;

import com.ra.ss2lan2.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findProducts();
    boolean deleteProductById(Long id);
    Product findProductById(Long id);
    boolean saveProduct(Product product);
    boolean updateProduct(Product product);
}
