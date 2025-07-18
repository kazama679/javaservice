package com.ra.ss8.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseObject {
    private String status;
    private String message;
    private Object data;
}