package com.project.toyProject2.domain.dto;

import lombok.Data;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Data
public class OrderPaymentDTO {
    private String memberName;
    private String memberLoginId;
    private String memberPhone;
    private List<CartListDTO> cartList;
    private Integer totalPrice=0;
    private Integer deliveryPrice =3000;
    private Integer totalPaymentPrice=0;

    public String formatDeliveryPrice(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return formatter.format(this.deliveryPrice);
    }
    public String formatTotalPrice(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return formatter.format(this.totalPrice);
    }
    public String formatTotalPaymentPrice(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return formatter.format(this.totalPaymentPrice);
    }
}
