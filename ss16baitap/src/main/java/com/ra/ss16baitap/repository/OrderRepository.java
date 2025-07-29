package com.ra.ss16baitap.repository;

import com.ra.ss16baitap.model.entity.Order;
import com.ra.ss16baitap.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE DATE(o.createdDate) = :day")
    BigDecimal getRevenueByDay(@Param("day") LocalDate day);
    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE MONTH(o.createdDate) = :month AND YEAR(o.createdDate) = :year")
    BigDecimal getRevenueByMonth(@Param("month") int month, @Param("year") int year);
    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE YEAR(o.createdDate) = :year")
    BigDecimal getRevenueByYear(@Param("year") int year);
}