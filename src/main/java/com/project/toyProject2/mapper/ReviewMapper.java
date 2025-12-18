package com.project.toyProject2.mapper;

import com.project.toyProject2.domain.dto.review.ReviewListDTO;
import com.project.toyProject2.domain.vo.ReviewVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {

    public void insert(ReviewVO reviewVO);
//    해당 제품의 리뷰목록 불러오기
    public List<ReviewListDTO> selectAll(Long productId);
    public Double selectRatingAvg(Long productId);
}
