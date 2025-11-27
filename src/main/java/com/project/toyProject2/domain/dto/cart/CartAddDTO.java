package com.project.toyProject2.domain.dto.cart;

import lombok.Data;

@Data
public class CartAddDTO {
    private Long memberId;
    private Long productId;
    private Long quantity;
}
