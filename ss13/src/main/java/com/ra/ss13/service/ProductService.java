package com.ra.ss13.service;

import com.ra.ss13.model.entity.ProductBt;

import java.util.List;

public interface ProductService {
    ProductBt save(ProductBt product);
    ProductBt update(Long id, ProductBt updatedProduct);
    void delete(Long id);
    List<ProductBt> findAll();
    ProductBt findById(Long id);
    List<ProductBt> getAll();
}
