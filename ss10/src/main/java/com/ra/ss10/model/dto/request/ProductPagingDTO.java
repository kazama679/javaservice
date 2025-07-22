package com.ra.ss10.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPagingDTO {
    private String proName;
    private Integer page;
    private Integer itemPage;
    private String sortBy;
    private Boolean orderBy;
}