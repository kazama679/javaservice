package com.ra.ss15baitap.service;

import com.ra.ss15baitap.model.dto.request.FeedbackRequest;
import com.ra.ss15baitap.model.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback createFeedback(Long userId, FeedbackRequest request);
    List<Feedback> getAllFeedback();
    Feedback replyFeedback(Long feedbackId, String reply);
}
