package com.project.toyProject2.repository;

import com.project.toyProject2.domain.vo.WishListVO;
import com.project.toyProject2.mapper.WishListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor

public class WishListDAO {
    private final WishListMapper wishListMapper;
    public void insertWishList(WishListVO wishListVO) {
        wishListMapper.insert(wishListVO);
    }
    public WishListVO selectWishProductById(Long memberId,Long productId) {
        return wishListMapper.selectWishProduct(memberId,productId);
    }
    public List<WishListVO> selectAllByMemberId(Long memberId){
        return wishListMapper.selectAllByMemberId(memberId);
    }
    public void deleteWishProductById(Long memberId,Long productId) {
        wishListMapper.delete(memberId,productId);
    }
}
