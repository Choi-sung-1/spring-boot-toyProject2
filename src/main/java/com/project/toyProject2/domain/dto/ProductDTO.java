package com.project.toyProject2.domain.dto;

import com.project.toyProject2.domain.vo.ImageVO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductDTO {
        private Long productId;
        private String productName;
        private String productDescription;
        private Integer productPrice;
        private Integer productStock;
        private LocalDateTime productCreateTime;
        private LocalDateTime productUpdateTime;
        private List<ImageVO> images;

}
