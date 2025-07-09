package com.ra.ss2lan2.service;

import com.ra.ss2lan2.model.entity.Product;
import com.ra.ss2lan2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    @Override
    public boolean deleteProductById(Long id) {
        productRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Khong tim thay!"));
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Khong tim thay!"));
    }

    @Override
    public boolean saveProduct(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        productRepository.findById(product.getProductId()).orElseThrow(()-> new NoSuchElementException("Khong tim thay!"));
        try {
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
