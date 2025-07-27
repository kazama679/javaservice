package com.ra.ss14.model.dto.request;

import lombok.Data;

@Data
public class OtpVerifyRequest {
    private String username;
    private String password;
    private String otp;
}