package com.ra.ss5.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FruitDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer stock;
}
