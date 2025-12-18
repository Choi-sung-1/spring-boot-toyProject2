package com.project.toyProject2.service.review;

import com.project.toyProject2.domain.dto.review.ReviewListDTO;
import com.project.toyProject2.domain.vo.ReviewVO;
import com.project.toyProject2.repository.ReviewDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ReviewServiceImpl implements ReviewService {
    private final ReviewDAO reviewDAO;
    @Override
    public void writeReview(ReviewVO reviewVO) {
        reviewDAO.insert(reviewVO);
    }

    @Override
    public List<ReviewListDTO> reviewListPage(Long productId) {
        return reviewDAO.selectAll(productId);
    }
}
