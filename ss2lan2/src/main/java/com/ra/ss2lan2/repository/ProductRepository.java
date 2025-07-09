package com.ra.ss2lan2.repository;

import com.ra.ss2lan2.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p")
    List<Product> findProducts();

    // tim kiem theo ten nha san xuat va nam san xuat
//    List<Product> findProductsByProducerContainsAndYearProducts(String producer, Integer producerMaking);
}
