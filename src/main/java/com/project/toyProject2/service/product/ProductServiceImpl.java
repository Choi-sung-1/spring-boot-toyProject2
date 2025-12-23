package com.project.toyProject2.service.product;

import com.project.toyProject2.domain.dto.product.ProductDetailDTO;
import com.project.toyProject2.domain.dto.product.ProductListDTO;
import com.project.toyProject2.domain.dto.product.ProductListRequestDTO;
import com.project.toyProject2.domain.vo.ImageVO;
import com.project.toyProject2.domain.vo.ProductVO;
import com.project.toyProject2.domain.vo.WishListVO;
import com.project.toyProject2.repository.ImageDAO;
import com.project.toyProject2.repository.ProductDAO;
import com.project.toyProject2.repository.ReviewDAO;
import com.project.toyProject2.repository.WishListDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.project.toyProject2.controller.ProductController.PAGE_SIZE;

@Slf4j
@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;
    private final ImageDAO imageDAO;
    private final WishListDAO wishListDAO;
    private final ReviewDAO reviewDAO;

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
                image.setReferenceId(productVO.getProductId());
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
        productDetailDTO.setReviewRatingAvg(reviewDAO.selectRatingAvg(productId));
        WishListVO findProduct = wishListDAO.selectWishProductById(memberId,productId);
            if(findProduct==null) {
                productDetailDTO.setWishStatus(false);
            }else {
                productDetailDTO.setWishStatus(true);
            }
        return productDetailDTO;
    }

    @Override
    public ProductVO findProductByPk(Long productId) {
        return productDAO.selectProductById(productId);
    }

    //    전체 상품 조회
    @Override
    public List<ProductListDTO> findAllProduct(ProductListRequestDTO productListRequestDTO) {
        if (productListRequestDTO.getCurrentPage()==null){
            productListRequestDTO.setCurrentPage(1);
        }
        int totalProduct = productDAO.selectProductCount(productListRequestDTO.getKeyword());
        productListRequestDTO.setTotalPage((totalProduct==0)?1:(int)Math.ceil((double)totalProduct/PAGE_SIZE));   //총 페이지 수 3개
        int pageBlockSize = 5; //한번에 보여줄 페이지 블록 수 ex)1,2,3,4,5 혹은 6,7,8,9,10
        productListRequestDTO.setStartPage(Math.max(1,productListRequestDTO.getCurrentPage()-2));
        productListRequestDTO.setEndPage(Math.min(productListRequestDTO.getTotalPage(),productListRequestDTO.getStartPage()+pageBlockSize-1));

        productListRequestDTO.setPageSize(PAGE_SIZE);
        productListRequestDTO.setStartRow((productListRequestDTO.getCurrentPage()-1)*PAGE_SIZE);
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
    public void deleteProduct(Long productId) {
        imageDAO.delete("PRODUCT",productId);
        productDAO.deleteProductById(productId);
    }
}
