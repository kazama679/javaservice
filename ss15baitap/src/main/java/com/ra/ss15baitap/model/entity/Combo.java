package com.ra.ss15baitap.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Combo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String items;
    @Enumerated(EnumType.STRING)
    private Status status;
    public enum Status {
        ACTIVE, INACTIVE
    }
}
