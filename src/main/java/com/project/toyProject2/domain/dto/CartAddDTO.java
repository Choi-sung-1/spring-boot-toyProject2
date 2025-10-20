package com.project.toyProject2.domain.dto;

import lombok.Data;

@Data
public class CartAddDTO {
    private Long memberId;
    private Long productId;
    private Long quantity;
}
