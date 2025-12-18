package com.project.toyProject2.service.review;

import com.project.toyProject2.domain.dto.review.ReviewListDTO;
import com.project.toyProject2.domain.vo.ReviewVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewService {
    public void writeReview(ReviewVO reviewVO);
    public List<ReviewListDTO> reviewListPage(Long productId);
}
