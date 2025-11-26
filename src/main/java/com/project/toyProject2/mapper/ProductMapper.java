package com.project.toyProject2.mapper;

import com.project.toyProject2.domain.dto.ProductListDTO;
import com.project.toyProject2.domain.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
//    상품 등록
    public void insert(ProductVO productVO);
//    상품 조회
    public ProductVO selectById(Long productId);
//    전체 상품 조회
    public List<ProductListDTO> selectAll();
//    상품 업데이트
    public void update(Long productId,Integer productStock);
//    상품 조회수 업데이트
    public void updateReadCount(Long productId);
//    상품 삭제
    public void delete(Long productId);
}
