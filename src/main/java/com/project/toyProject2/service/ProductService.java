package com.project.toyProject2.service;
import com.project.toyProject2.domain.vo.ProductVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
//    상품 등록
    public void saveProduct(ProductVO productVO, MultipartFile[] files);
//    상품 조회
    public ProductVO findProduct(Long id);
//    상품 전체 조회
    public List<ProductVO> findAllProduct();
//    상품 업데이트
    public void updateProduct(Long productId,Integer productStock);
//    상품 제거
    public void deleteProduct(Long id);
}
