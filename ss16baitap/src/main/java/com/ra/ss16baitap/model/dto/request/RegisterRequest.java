package com.ra.ss16baitap.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank
    private String email;
    @NotBlank private String phone;
    @NotBlank private String password;
}