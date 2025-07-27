package com.ra.ss14.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendOtp(String toEmail, String otp) {
        System.out.println("Gửi OTP " + otp + " tới email: " + toEmail);
    }
}
