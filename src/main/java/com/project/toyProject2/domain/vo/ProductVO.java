package com.project.toyProject2.domain.vo;

import lombok.Data;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;

@Data
public class ProductVO {
    private Long productId;
    private String productName;
    private String productDescription;
    private Integer productPrice;
    private Integer productStock;
    private LocalDateTime productCreateTime;
    private LocalDateTime productUpdateTime;

    public String formatProductPrice(){
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return format.format(productPrice);
    }
}
