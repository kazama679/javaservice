package com.ra.ss10.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private UUID id;
    @Column(name = "fullname", length = 50)
    private String fullname;
    @Column(name = "phone", length = 20)
    private String phone;
    @Column(name = "cccd", length = 12)
    private String cccd;
    @Column(name = "email", length = 100)
    private String email;
    @Column(name = "money")
    private Double money;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
}
