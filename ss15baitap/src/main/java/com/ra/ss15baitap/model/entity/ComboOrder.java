package com.ra.ss15baitap.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ComboOrder {
    @Id @GeneratedValue
    private Long id;
    private Long comboId;
    private Integer quantity;
    private LocalDateTime createdAt;
}
