package com.project.toyProject2.service;

import com.project.toyProject2.domain.vo.ImageVO;
import com.project.toyProject2.domain.vo.ProductVO;
import com.project.toyProject2.repository.ImageDAO;
import com.project.toyProject2.repository.ProductDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;
    private final ImageDAO imageDAO;
//    상품 등록
    @Override
    public void saveProduct(ProductVO productVO, MultipartFile[] files) {
        productDAO.insertProduct(productVO);
        if (files != null) {
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    continue;
                }
                String fileName = file.getOriginalFilename();
                String filePath = "/Users/ChoiSungWon/Documents/Develope/spring/project/toyProject2/src/main/resources/static/images";

                File dir = new File(filePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File targetFile = new File(dir,fileName);
                try {
                    file.transferTo(targetFile);
                } catch (IOException e) {
                    throw new RuntimeException("파일 저장 실패", e);
                }

                ImageVO image = new ImageVO();
                image.setImageOriginalName(fileName);
                UUID uuid = UUID.randomUUID();
                image.setImagePath(uuid+"_"+filePath);
                image.setImageType("PRODUCT");
                image.setProductId(productVO.getProductId());

                imageDAO.insert(image);
            }
        }
    }
//    상품 조회
    @Override
    public ProductVO findProduct(Long id) {
        return productDAO.selectProductById(id);
    }
//    전체 상품 조회
    @Override
    public List<ProductVO> findAllProduct() {
        return productDAO.selectAllProducts();
    }
//    상품 업데이트
    @Override
    public void updateProduct(ProductVO productVO) {
        productDAO.updateProduct(productVO);
    }
//    상품 삭제
    @Override
    public void deleteProduct(Long id) {
        imageDAO.delete(id);
        productDAO.deleteProductById(id);
    }
}
