package com.project.toyProject2.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewVO {
    private Long reviewId;
    private Long memberId;
    private Long productId;
    private String reviewTitle;
    private String reviewContent;
    private Integer reviewRating;
    private LocalDateTime reviewCrateDate;
}
