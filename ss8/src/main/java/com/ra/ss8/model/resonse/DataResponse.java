package com.ra.ss8.model.resonse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class DataResponse <T>{
    private T data;
    private String message;
    private Long timestamp = System.currentTimeMillis();
}