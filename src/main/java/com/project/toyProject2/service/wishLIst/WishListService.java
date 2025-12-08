package com.project.toyProject2.service.wishLIst;

import com.project.toyProject2.domain.dto.wishList.WishListPageDTO;
import com.project.toyProject2.domain.vo.WishListVO;

import java.util.List;

public interface WishListService {
    public void saveWishList(WishListVO wishListVO);
    public WishListVO findWishProductById(Long memberId,Long productId);
    public List<WishListPageDTO> getWishListByMemberId(Long memberId);
    public void deleteWishList(Long memberId,Long productId);
}
