package com.ra.ss3lan2.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @Column(name = "category_id", length = 15)
    private String cateId;
    @Column(name = "category_name", length = 100, nullable = false, unique = true)
    private String cateName;

    @OneToMany(mappedBy = "cate")
    private List<Product> products;
}
