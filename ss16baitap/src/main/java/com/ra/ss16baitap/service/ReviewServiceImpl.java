package com.ra.ss16baitap.service;

import com.ra.ss16baitap.model.entity.Review;
import com.ra.ss16baitap.repository.OrderRepository;
import com.ra.ss16baitap.repository.ProductRepository;
import com.ra.ss16baitap.repository.ReviewRepository;
import com.ra.ss16baitap.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepo;
    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    @Override
    public Review addReview(Long userId, Long productId, int rating, String comment) {
        boolean hasPurchased = orderRepo.existsByUserIdAndProductId(userId, productId);
        if (!hasPurchased) throw new AccessDeniedException("Bạn chưa mua sản phẩm này!");
        Review review = Review.builder()
                .user(userRepo.findById(userId).orElseThrow())
                .product(productRepo.findById(productId).orElseThrow())
                .rating(rating)
                .comment(comment)
                .createdDate(LocalDateTime.now())
                .build();
        return reviewRepo.save(review);
    }

    @Override
    public List<Review> getReviewsByProduct(Long productId) {
        return reviewRepo.findByProductId(productId);
    }
}
