package com.project.toyProject2.repository;

import com.project.toyProject2.mapper.ProductMapper;
import com.project.toyProject2.domain.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDAO {
    private final ProductMapper productMapper;

//    상품 등록
    public void insertProduct(ProductVO productVO) {
        productMapper.insert(productVO);
    }
//    상품 조회 by pk
    public ProductVO selectProductById(Long id) {
        return productMapper.selectById(id);
    }
//    상품 전체 조회
    public List<ProductVO> selectAllProducts() {
        return productMapper.selectAll();
    }
//    상품 업데이트
    public void updateProduct(Long productId,Integer productStock) {
        productMapper.update(productId,productStock);
    }
//    상품 제거
    public void deleteProductById(Long id) {
        productMapper.delete(id);
    }
}
