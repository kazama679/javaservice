package com.ra.ss12.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users_demo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDemo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
}
