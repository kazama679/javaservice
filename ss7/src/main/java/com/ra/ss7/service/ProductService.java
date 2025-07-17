package com.ra.ss7.service;

import com.ra.ss7.model.entity.Product;
import com.ra.ss7.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product add(Product product) {
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID = " + id));
    }

    public Product updateProduct(Long id, Product product) {
        return productRepository.findById(id).map(p -> {
            p.setProduct_id(product.getProduct_id());
            p.setProduct_name(product.getProduct_name());
            p.setProducer(product.getProducer());
            return productRepository.save(product);
        }).orElse(null);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
