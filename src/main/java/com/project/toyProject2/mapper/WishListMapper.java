package com.project.toyProject2.mapper;

import com.project.toyProject2.domain.vo.WishListVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WishListMapper {
    public void insert(WishListVO wishListVO);
    public WishListVO selectWishProduct(Long memberId,Long productId);
    public List<WishListVO> selectAllByMemberId(Long memberId);
    public void delete(Long memberId,Long productId);
}
