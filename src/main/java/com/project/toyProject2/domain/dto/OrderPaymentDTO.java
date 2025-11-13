package com.project.toyProject2.domain.dto;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Data
@Slf4j
public class OrderPaymentDTO {
    private String memberName;
    private String memberLoginId;
    private String memberPhone;
    private List<CartListDTO> cartLists;
    private Integer totalProductPrice=0;
    private Integer deliveryPrice = 3000;
    private Integer totalPayment = 0;

    @PostConstruct
    public void calcTotalPrice(){
        for(CartListDTO cartListDTO : cartLists){
            totalProductPrice += cartListDTO.getTotalPrice();
        }
        totalPayment = totalProductPrice+deliveryPrice;
    }
    public String formatTotalPayment(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return formatter.format(this.totalPayment);
    }
    public String formatDeliveryPrice(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return formatter.format(this.deliveryPrice);
    }
    public String formatTotalProductPrice(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return formatter.format(this.totalPayment);
    }
}
