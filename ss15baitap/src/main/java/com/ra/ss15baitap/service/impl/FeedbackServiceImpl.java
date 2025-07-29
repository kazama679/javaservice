package com.ra.ss15baitap.service.impl;
import com.ra.ss15baitap.model.dto.request.FeedbackRequest;
import com.ra.ss15baitap.model.entity.Combo;
import com.ra.ss15baitap.model.entity.Feedback;
import com.ra.ss15baitap.model.entity.PlayArea;
import com.ra.ss15baitap.model.entity.User;
import com.ra.ss15baitap.repository.ComboRepository;
import com.ra.ss15baitap.repository.FeedbackRepository;
import com.ra.ss15baitap.repository.PlayAreaRepository;
import com.ra.ss15baitap.repository.UserRepository;
import com.ra.ss15baitap.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepo;
    private final UserRepository userRepo;
    private final ComboRepository comboRepo;
    private final PlayAreaRepository playAreaRepo;

    @Override
    public Feedback createFeedback(Long userId, FeedbackRequest request) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Combo combo = request.getComboId() != null ? comboRepo.findById(request.getComboId()).orElse(null) : null;
        PlayArea playArea = request.getPlayAreaId() != null ? playAreaRepo.findById(request.getPlayAreaId()).orElse(null) : null;
        boolean usedService = false;
        if (playArea != null && feedbackRepo.existsByUserAndPlayArea(user, playArea)) usedService = true;
        if (combo != null && feedbackRepo.existsByUserAndCombo(user, combo)) usedService = true;
        if (!usedService) {
            throw new RuntimeException("Bạn chưa từng sử dụng dịch vụ này để đánh giá.");
        }
        Feedback feedback = Feedback.builder()
                .user(user)
                .playArea(playArea)
                .combo(combo)
                .rating(request.getRating())
                .comment(request.getComment())
                .createdAt(LocalDateTime.now())
                .build();
        return feedbackRepo.save(feedback);
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepo.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Override
    public Feedback replyFeedback(Long feedbackId, String reply) {
        Feedback feedback = feedbackRepo.findById(feedbackId)
                .orElseThrow(() -> new RuntimeException("Feedback không tồn tại"));
        feedback.setReply(reply);
        feedback.setRepliedAt(LocalDateTime.now());
        return feedbackRepo.save(feedback);
    }
}
