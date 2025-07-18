package com.ra.ss8.model.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private String fullName;
    private String phone;
    private String email;
    private Integer numberOfPayments;
}
