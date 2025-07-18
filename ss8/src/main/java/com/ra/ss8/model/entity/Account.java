package com.ra.ss8.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer id;
    @Column(name = "username", length=100, nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "fullname", length=70, nullable = false)
    private String fullname;
    @Column(name = "gender")
    private Boolean gender;
    @Column(name = "address", length=200)
    private String address;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "phone", length = 20, unique = true)
    private String phone;
}