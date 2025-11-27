package com.project.toyProject2.domain.dto.order;

import lombok.Data;

@Data
public class OrderRequestDTO {
    private String memberLoginId;
    private Integer totalProductPrice;
    private Integer deliveryPrice;
    private Integer totalPayment;

    private String postcode;
    private String orderAddress;
    private String orderDetailAddress;
    private String orderMemo;
}
