package com.ra.ss15baitap.model.dto.request;

import lombok.Data;

@Data
public class FeedbackRequest {
    private Long playAreaId;
    private Long comboId;
    private Integer rating;
    private String comment;
}
