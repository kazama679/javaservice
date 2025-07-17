package com.ra.ss7.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_details")
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_detail_id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer year_making;
    private String color;
    private String size;
    private Double price;
}
