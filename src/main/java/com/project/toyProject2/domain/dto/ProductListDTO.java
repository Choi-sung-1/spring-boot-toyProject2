package com.project.toyProject2.domain.dto;

import lombok.Data;

import java.text.NumberFormat;
import java.util.Locale;

@Data
public class ProductListDTO {
    private Long productId;
    private String productName;
    private Integer productPrice;
    private Integer productReadCount;
    private String imageOriginalName;


    public String formatProductPrice() {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return format.format(productPrice);
    }
}
