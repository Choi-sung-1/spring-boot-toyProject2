package com.project.toyProject2.domain.dto.product;

import lombok.Data;

@Data
public class ProductListRequestDTO {
    private String keyword;
    private String sort;
}
