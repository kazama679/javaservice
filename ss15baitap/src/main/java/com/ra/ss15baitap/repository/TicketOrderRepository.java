package com.ra.ss15baitap.repository;

import com.ra.ss15baitap.model.entity.TicketOrder;
import com.ra.ss15baitap.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface TicketOrderRepository extends JpaRepository<TicketOrder, Long> {
    List<TicketOrder> findByUser(User user);
    @Query(value = """
        SELECT COALESCE(SUM(t.total_price), 0) 
        FROM ticket_order t 
        WHERE DATE_FORMAT(t.created_at, :format) = :value
    """, nativeQuery = true)
    BigDecimal getRevenueByTime(@org.springframework.data.repository.query.Param("format") String format,
                                @org.springframework.data.repository.query.Param("value") String value);

    @Query(value = """
        SELECT COUNT(*) 
        FROM ticket_order t 
        WHERE DATE_FORMAT(t.created_at, :format) = :value
    """, nativeQuery = true)
    Long getTicketsSoldByTime(@org.springframework.data.repository.query.Param("format") String format,
                              @org.springframework.data.repository.query.Param("value") String value);
}