package com.ra.ss12.service.impl;

import com.ra.ss12.model.entity.Product;
import com.ra.ss12.repository.ProductRepository;
import com.ra.ss12.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        Optional<Product> existing = productRepository.findById(id);
        if (existing.isPresent()) {
            Product p = existing.get();
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            p.setDescription(product.getDescription());
            return productRepository.save(p);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
