package com.project.toyProject2.domain.dto.product;

import lombok.Data;

@Data
public class ProductListRequestDTO {
    private String keyword;
    private String sort;
    private Integer currentPage ;
    private Integer startPage ;
    private Integer endPage;
    private Integer page;
    private Integer totalPage;
    private Integer totalProductCount;

    private Integer startRow;
    private Integer pageSize;
}
