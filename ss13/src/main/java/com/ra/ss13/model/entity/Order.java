package com.ra.ss13.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private String receiver;
    private String address;
    private String phoneNumber;
    private Double totalMoney;
    private LocalDateTime orderDate;
    private String status;

    @ManyToOne
    private UserBt user;
}
