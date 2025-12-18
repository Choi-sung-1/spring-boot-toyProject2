package com.project.toyProject2.repository;

import com.project.toyProject2.domain.dto.review.ReviewListDTO;
import com.project.toyProject2.domain.vo.ReviewVO;
import com.project.toyProject2.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewDAO {
    private final ReviewMapper reviewMapper;

    public void insert(ReviewVO reviewVO) {
        reviewMapper.insert(reviewVO);
    }

    public List<ReviewListDTO> selectAll(Long productId) {
        return reviewMapper.selectAll(productId);
    }
    public Double selectRatingAvg(Long productId) {
        return reviewMapper.selectRatingAvg(productId);
    }
}
