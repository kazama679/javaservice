package com.ra.ss13.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductBt {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Integer quantity;
}
