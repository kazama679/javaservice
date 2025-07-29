package com.ra.ss15baitap.repository;

import com.ra.ss15baitap.model.entity.ComboOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ComboOrderRepository extends JpaRepository<ComboOrder, Long> {
    @Query("SELECT SUM(c.quantity) FROM ComboOrder c WHERE FUNCTION('DATE_FORMAT', c.createdAt, :format) = :value")
    Long getComboUsedByTime(@Param("format") String format, @Param("value") String value);
}