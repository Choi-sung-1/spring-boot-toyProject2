package com.project.toyProject2.domain.vo;

import lombok.Data;

@Data
public class OrderItemVO {
    private Long orderItemId;
    private Long orderId;
    private Long orderProductId;
    private Integer orderItemQuantity;
    private Integer orderItemPrice;
}
