package com.project.toyProject2.service;

import com.project.toyProject2.domain.dto.CartAddDTO;
import com.project.toyProject2.domain.dto.CartListDTO;
import com.project.toyProject2.domain.vo.CartVO;

import java.util.List;

public interface CartService {
    public void addCart(CartAddDTO cartDTO);
    public CartVO findCartItem(Long memberId,Long productID);
    public List<CartListDTO> findAllCartByMemberId(Long memberId);
    public void deleteCartItem(Long cartId);
    public void deleteAllCartItemByMemberId(Long memberId);
}
