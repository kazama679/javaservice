package com.ra.ss16baitap.repository;

import com.ra.ss16baitap.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
