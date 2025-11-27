package com.project.toyProject2.service;

import com.project.toyProject2.domain.dto.cart.CartAddDTO;
import com.project.toyProject2.domain.dto.cart.CartListDTO;
import com.project.toyProject2.domain.vo.CartVO;
import com.project.toyProject2.repository.CartDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
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
    public void updateCartItemQuantity(Long cartId, Long cartQuantity) {
        cartDAO.updateCartQuantity(cartId, cartQuantity);
    }

    @Override
    public List<CartListDTO> findAllCartByMemberId(Long memberId) {
        List<CartListDTO> findCartList = cartDAO.selectAll(memberId);
        Map<Long, CartListDTO> map = new LinkedHashMap<>();

        for (CartListDTO cartListDTO : findCartList) {
            if (!map.containsKey(cartListDTO.getProductId())){
                map.put(cartListDTO.getProductId(), cartListDTO);
            }
        }
        return new ArrayList<>(map.values());
    }

    @Override
    public void deleteCartItem(Long cartId) {
        cartDAO.delete(cartId);
    }

    @Override
    public void deleteAllCartItemByMemberId(Long memberId) {
        cartDAO.deleteAll(memberId);
    }
}
