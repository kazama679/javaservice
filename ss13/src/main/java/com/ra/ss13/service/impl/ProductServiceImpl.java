package com.ra.ss13.service.impl;

import com.ra.ss13.model.entity.ProductBt;
import com.ra.ss13.repository.ProductRepository;
import com.ra.ss13.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductBt> getAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductBt save(ProductBt product) {
        return productRepository.save(product);
    }

    @Override
    public ProductBt update(Long id, ProductBt updatedProduct) {
        ProductBt product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setDescription(updatedProduct.getDescription());
        product.setQuantity(updatedProduct.getQuantity());
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductBt> findAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductBt findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ko tim thay san pham"));
    }
}
