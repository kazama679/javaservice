package com.ra.ss8.model.resonse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class DataErrorResponse <T>{
    private String message;
    private T error;
    private HttpStatus status;
}
