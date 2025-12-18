package com.project.toyProject2.domain.dto.review;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ReviewListDTO {
    private String memberLoginId;
    private Long reviewRating;
    private String reviewTitle;
    private String reviewContent;
    private LocalDateTime reviewCreateDate;

    public String getFormatReviewDate() {
        return reviewCreateDate.format(DateTimeFormatter.ofPattern("yyy.MM.dd"));
    }
}
