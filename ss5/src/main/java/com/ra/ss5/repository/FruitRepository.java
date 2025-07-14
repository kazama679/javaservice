package com.ra.ss5.repository;

import com.ra.ss5.model.entity.FruitProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FruitRepository extends JpaRepository<FruitProduct, Long> {
}
