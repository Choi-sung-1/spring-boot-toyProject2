package com.project.toyProject2.service;

import com.project.toyProject2.domain.dto.product.ProductDetailDTO;
import com.project.toyProject2.domain.dto.product.ProductListDTO;
import com.project.toyProject2.domain.dto.product.ProductListRequestDTO;
import com.project.toyProject2.domain.vo.ImageVO;
import com.project.toyProject2.domain.vo.ProductVO;
import com.project.toyProject2.domain.vo.WishListVO;
import com.project.toyProject2.repository.ImageDAO;
import com.project.toyProject2.repository.ProductDAO;
import com.project.toyProject2.repository.WishListDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;
    private final ImageDAO imageDAO;
    private final WishListDAO wishListDAO;
//    상품 등록
    @Override
    public void saveProduct(ProductVO productVO, MultipartFile[] files) {
        productDAO.insertProduct(productVO);
        if (files != null) {
            int index=0;
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
                image.setMainImage(index == 0 ? "Y":"N");
                image.setImagePath(uuid+"_"+filePath);
                image.setImageType("PRODUCT");
                image.setProductId(productVO.getProductId());

                index++;
                imageDAO.insert(image);
            }
        }
    }
//    상품 조회
    @Override
    public ProductDetailDTO productDetailPage(Long productId,Long memberId) {
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        ProductVO productVO = productDAO.selectProductById(productId);

        productDetailDTO.setProductId(productVO.getProductId());
        productDetailDTO.setProductName(productVO.getProductName());
        productDetailDTO.setProductDescription(productVO.getProductDescription());
        productDetailDTO.setProductPrice(productVO.getProductPrice());
        productDetailDTO.setProductStock(productVO.getProductStock());

        WishListVO findProduct = wishListDAO.selectWishProductById(memberId,productId);
            if(findProduct==null) {
                productDetailDTO.setWishStatus(false);
            }else {
                productDetailDTO.setWishStatus(true);
            }
        return productDetailDTO;
    }
//    전체 상품 조회
    @Override
    public List<ProductListDTO> findAllProduct(ProductListRequestDTO productListRequestDTO) {
        return productDAO.selectAllProducts(productListRequestDTO);
    }
//    상품 업데이트
    @Override
    public void updateProduct(Long productId,Integer productStock) {
        productDAO.updateProduct(productId,productStock);
    }

    @Override
    public void updateReadCount(Long productId) {
        productDAO.updateProductReadCount(productId);
    }

    //    상품 삭제
    @Override
    public void deleteProduct(Long id) {
        imageDAO.delete(id);
        productDAO.deleteProductById(id);
    }
}
