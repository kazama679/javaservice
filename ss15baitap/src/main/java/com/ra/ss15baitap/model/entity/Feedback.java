package com.ra.ss15baitap.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private PlayArea playArea;
    @ManyToOne
    private Combo combo;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;
    private String reply;
    private LocalDateTime repliedAt;
}
