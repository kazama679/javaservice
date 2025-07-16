package com.ra.ss6.repository;

import com.ra.ss6.model.entity.ProductCart;
import com.ra.ss6.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {
    List<ProductCart> findByUser(User user);
}
