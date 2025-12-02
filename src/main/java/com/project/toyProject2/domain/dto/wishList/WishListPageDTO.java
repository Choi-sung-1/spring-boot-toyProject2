package com.project.toyProject2.domain.dto.wishList;

import lombok.Data;

@Data
public class WishListPageDTO {
    private String imageOriginalName;
    private Long productId;
    private String productName;
    private String productPrice;
    private Integer productReadCount;
    private Boolean wishStatus = true;

}
