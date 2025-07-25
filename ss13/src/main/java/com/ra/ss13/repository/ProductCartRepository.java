package com.ra.ss13.repository;

import com.ra.ss13.model.entity.ProductCart;
import com.ra.ss13.model.entity.UserBt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {
    List<ProductCart> findByUser(UserBt user);
    Optional<ProductCart> findByUserAndProduct_Id(UserBt user, Long productId);
    void deleteByUserAndProduct_Id(UserBt user, Long productId);
}
