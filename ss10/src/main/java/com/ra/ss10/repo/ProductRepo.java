package com.ra.ss10.repo;

import com.ra.ss10.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
    Page<Product> findProductsByProName(String proName, Pageable pageable);
    Page<Product> findProductsByProNameContaining(String proName, Pageable pageable);
}