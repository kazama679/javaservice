package com.ra.ss3lan2.service;

import com.ra.ss3lan2.model.entity.Product;
import com.ra.ss3lan2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean deleteProductById(String id) {
        return false;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(String id) {
        return null;
    }

    @Override
    public boolean saveProduct(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editProduct(Product product) {
        return false;
    }
}
