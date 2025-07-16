package com.ra.ss6.service;
import com.ra.ss6.model.dto.ProductPagination;
import com.ra.ss6.model.entity.Product;
import com.ra.ss6.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductPagination getAllProducts(Pageable pageable, String searchName) {
        Page<Product> pageResult;

        if (searchName == null || searchName.trim().isEmpty()) {
            pageResult = productRepository.findAll(pageable);
        } else {
            pageResult = productRepository.findByNameContainingIgnoreCase(searchName, pageable);
        }

        return new ProductPagination(
                pageResult.getContent(),
                pageResult.getTotalPages(),
                pageResult.getSize(),
                pageResult.getNumber()
        );
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id).map(product -> {
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            product.setDescription(productDetails.getDescription());
            product.setStock(productDetails.getStock());
            return productRepository.save(product);
        }).orElse(null);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
