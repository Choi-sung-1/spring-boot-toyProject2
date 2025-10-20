package com.project.toyProject2.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CartVO {
    private Long cartId;
    private Long memberId;
    private Long productId;
    private Long cartQuantity;
    private LocalDateTime cartCreateDate;
    private LocalDateTime cartUpdateDate;
}
