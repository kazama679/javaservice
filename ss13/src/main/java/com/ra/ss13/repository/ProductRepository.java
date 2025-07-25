package com.ra.ss13.repository;
import com.ra.ss13.model.entity.ProductBt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductBt, Long> {
}
