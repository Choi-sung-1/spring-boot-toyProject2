package com.project.toyProject2.domain.dto.product;

import lombok.Data;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;

@Data
public class ProductListDTO {
    private Long productId;
    private String productName;
    private Integer productPrice;
    private Integer productReadCount;
    private LocalDateTime productCreateDate;
    private String imageOriginalName;


    public String formatProductPrice() {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return format.format(productPrice);
    }
}
