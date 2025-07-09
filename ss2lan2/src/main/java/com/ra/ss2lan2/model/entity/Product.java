package com.ra.ss2lan2.model.entity;

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
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(name = "product_name", length = 100)
    private String productName;
    @Column(name = "producer", length = 100)
    private String producer;
    @Column(name = "year_making")
    private Integer producerMaking;
    @Column(name = "exprice_date")
    private Date expireDate;
    @Column(name = "price")
    private Double price;

//    @ManyToOne
//    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
//    private Category category;
}
