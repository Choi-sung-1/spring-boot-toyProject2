package com.project.toyProject2.domain.vo;

import lombok.Data;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
public class OrderVO {
    private Long orderId;
    private Long memberId;
    private String orderSummary;
    private LocalDateTime orderDate;
    private String orderStatus;
    private Integer orderTotalProductPrice;
    private Integer orderDeliveryPrice;
    private Integer orderTotalPayment;
    private String orderPaymentMethod;
    private String orderAddress;
    private String orderDetailAddress;
    private String orderMemo;

    public String formatOrderTotalPayment(){
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return format.format(orderTotalPayment);
    }
    public String formatOrderTotalProductPrice(){
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return format.format(orderTotalProductPrice);
    }
    public String formatDeliveryPrice(){
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return format.format(orderDeliveryPrice);
    }
    public String formatOrderDate() {
        return orderDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
    }}
