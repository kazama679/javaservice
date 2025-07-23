package com.ra.ss12.model.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
}
