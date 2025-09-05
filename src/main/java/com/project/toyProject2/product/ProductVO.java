package com.project.toyProject2.product;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductVO {
    private Long productId;
    private String productName;
    private String productDescription;
    private Integer productPrice;
    private Integer productStock;
    private String productImagePath;
    private LocalDateTime productCreateTime;
    private LocalDateTime productUpdateTime;
}
