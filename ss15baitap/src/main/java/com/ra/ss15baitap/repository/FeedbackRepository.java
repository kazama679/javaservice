package com.ra.ss15baitap.repository;

import com.ra.ss15baitap.model.entity.Combo;
import com.ra.ss15baitap.model.entity.Feedback;
import com.ra.ss15baitap.model.entity.PlayArea;
import com.ra.ss15baitap.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByUserId(Long userId);
    boolean existsByUserAndPlayArea(User user, PlayArea playArea);
    boolean existsByUserAndCombo(User user, Combo combo);
}
