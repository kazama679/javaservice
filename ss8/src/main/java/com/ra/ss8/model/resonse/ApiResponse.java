package com.ra.ss8.model.resonse;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse <T>{
    private Boolean status;
    private String message;
    private T data;
}
