package com.ra.ss13.model.dto.request;

import lombok.Data;

@Data
public class CheckoutRequest {
    private String receiver;
    private String phone;
    private String address;
}
