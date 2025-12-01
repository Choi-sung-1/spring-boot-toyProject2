package com.project.toyProject2.service;

import com.project.toyProject2.domain.vo.WishListVO;
import com.project.toyProject2.repository.WishListDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {
    private final WishListDAO wishListDAO;
    @Override
    public void saveWishList(WishListVO wishListVO) {
        wishListDAO.insertWishList(wishListVO);
    }

    @Override
    public WishListVO findWishProductById(Long memberId, Long productId) {
        return wishListDAO.selectWishProductById(memberId, productId);
    }

    @Override
    public List<WishListVO> getWishListByMemberId(Long memberId) {
        return wishListDAO.selectAllByMemberId(memberId);
    }

    @Override
    public void deleteWishList(Long memberId, Long productId) {
        wishListDAO.deleteWishProductById(memberId, productId);
    }

}
