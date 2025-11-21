package com.project.toyProject2.domain.dto;

import lombok.Data;

import java.text.NumberFormat;
import java.util.Locale;

@Data
public class OrderItemDetailDTO {
    private Long orderItemId;
    private Long orderId;
    private Long productId;
    private Integer orderItemQuantity;
    private String productName;
    private Integer productPrice;

    public String calProductTotalPrice(){
        Integer result = this.productPrice*this.orderItemQuantity;
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return formatter.format(result);
    }
    public String formatProductPrice(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return formatter.format(this.productPrice);
    }
}
