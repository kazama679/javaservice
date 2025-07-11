package com.ra.ss3lan2.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "product_name", length = 100, nullable = false, unique = true)
    private String productName;
    @Column(name = "producer", length = 100)
    private String producer;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category cate;
}
