package com.ra.ss13.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCart {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private UserBt user;

    @ManyToOne
    private ProductBt product;

    private Integer quantity;
}
