package com.ra.ss6.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stuId;

    private String fullName;
    private String gender;
    private LocalDate birthday;
    private String address;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Classes classes;
}
