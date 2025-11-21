package com.project.toyProject2.domain.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.util.Locale;

@Data
@Slf4j
public class CartListDTO {
    private Long cartId;
    private Long productId;
    private String imageOriginalName;
    private String productName;
    private Integer productPrice;
//    제품 수량
    private Integer productStock;
//    장바구니 수량
    private Integer cartQuantity;
    private Integer totalPrice=0;

    public Integer getTotalPrice() {
        totalPrice=productPrice*cartQuantity;
        return totalPrice;
    }

    public String formatProductPrice(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return formatter.format(this.productPrice);
    }
    public String formatTotalPrice(){
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return format.format(this.getTotalPrice());
    }
}
