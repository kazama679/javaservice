package com.ra.ss3lan2.model.dto;

public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;

    public EmployeeDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}