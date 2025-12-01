package com.project.toyProject2.service;

import com.project.toyProject2.domain.vo.WishListVO;

import java.util.List;

public interface WishListService {
    public void saveWishList(WishListVO wishListVO);
    public WishListVO findWishProductById(Long memberId,Long productId);
    public List<WishListVO> getWishListByMemberId(Long memberId);
    public void deleteWishList(Long memberId,Long productId);
}
