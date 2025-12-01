package com.project.toyProject2.domain.dto.product;

import lombok.Data;

import java.text.NumberFormat;
import java.util.Locale;

@Data
public class ProductDetailDTO {
    private Long productId;
    private String productName;
    private String productDescription;
    private Integer productPrice;
    private Integer productStock;
    private Boolean wishStatus;
    public String formatProductPrice(){
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return format.format(productPrice);
    }
}
