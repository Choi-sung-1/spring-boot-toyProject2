package com.project.toyProject2.service;

import com.project.toyProject2.domain.dto.CartAddDTO;
import com.project.toyProject2.domain.dto.CartListDTO;
import com.project.toyProject2.domain.vo.CartVO;
import com.project.toyProject2.repository.CartDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartDAO cartDAO;

    @Override
    public void addCart(CartAddDTO cartDTO) {
        CartVO cartVO = new CartVO();
        cartVO.setMemberId(cartDTO.getMemberId());
        cartVO.setProductId(cartDTO.getProductId());
        cartVO.setCartQuantity(cartDTO.getQuantity());
        cartDAO.insert(cartVO);
    }

    @Override
    public CartVO findCartItem(Long memberId, Long productID) {
        return cartDAO.select(memberId, productID);
    }

    @Override
    public List<CartListDTO> findAllCartByMemberId(Long memberId) {
        return cartDAO.selectAll(memberId);
    }

    @Override
    public void deleteCartItem(Long cartId) {
        cartDAO.delete(cartId);
    }
}
