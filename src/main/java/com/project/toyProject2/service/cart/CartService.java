package com.project.toyProject2.service.cart;

import com.project.toyProject2.domain.dto.cart.CartAddDTO;
import com.project.toyProject2.domain.dto.cart.CartListDTO;
import com.project.toyProject2.domain.vo.CartVO;

import java.util.List;

public interface CartService {
    public void addCart(CartAddDTO cartDTO);
    public CartVO findCartItem(Long memberId,Long productID);
    public void updateCartItemQuantity(Long cartId,Long cartQuantity);
    public List<CartListDTO> findAllCartByMemberId(Long memberId);
    public void deleteCartItem(Long cartId);
    public void deleteAllCartItemByMemberId(Long memberId);
}
