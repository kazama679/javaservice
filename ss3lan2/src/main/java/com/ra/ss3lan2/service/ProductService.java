package com.ra.ss3lan2.service;

import com.ra.ss3lan2.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(String id);
    boolean deleteProductById(String id);
    boolean saveProduct(Product product);
    boolean editProduct(Product product);
}
