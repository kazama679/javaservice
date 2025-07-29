package com.ra.ss16baitap.repository;


import com.ra.ss16baitap.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}