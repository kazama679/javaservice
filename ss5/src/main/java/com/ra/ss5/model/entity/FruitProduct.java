package com.ra.ss5.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "fruit_products")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FruitProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private Integer stock;
    private Boolean status;
    private LocalDate createdAt;
}