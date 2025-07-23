package com.ra.ss12.model.entity;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    private Long id;
    private String username;
    private String password;
    private boolean status;
    private Set<Role> roles;
}
