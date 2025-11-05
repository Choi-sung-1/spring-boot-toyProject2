package com.project.toyProject2.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderVO {
    private Long orderId;
    private Long memberId;
    private LocalDateTime orderDate;
    private String orderStatus;
    private Integer orderTotalPrice;
    private String orderPaymentMethod;
    private String orderAddress;
    private String orderDetailAddress;
    private String orderMemo;
}
