package com.ra.ss8.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishDTO {
    @NotBlank
    private String name;
    private String description;
    @NotNull
    private Double price;
    private String status;
    private MultipartFile image;
}
