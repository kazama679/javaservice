package com.ra.ss16baitap.service;

import com.ra.ss16baitap.model.entity.Review;

import java.util.List;

public interface ReviewService {
    Review addReview(Long userId, Long productId, int rating, String comment);
    List<Review> getReviewsByProduct(Long productId);
}
