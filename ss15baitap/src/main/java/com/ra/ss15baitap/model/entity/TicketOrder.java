package com.ra.ss15baitap.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantityTicket;

    private BigDecimal totalMoney;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "ticket_order_combo",
            joinColumns = @JoinColumn(name = "ticket_order_id"),
            inverseJoinColumns = @JoinColumn(name = "combo_id")
    )
    private List<Combo> combos;
}
